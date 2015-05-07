package bookBuilder.obk;

import fileManager.ZipReader;
import htmlRenderer.HtmlMarkupBuilder;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import tree.TreeNode;
import book.Book;
import book.contents.BookContents;
import book.contents.ChapterAddress;
import book.contents.DChapter;
import book.contents.IChapter;
import bookBuilder.IBookContentsBuilder;
import bookBuilder.SimpleSetingsParser;

public class OBK_Builder implements IBookContentsBuilder
{
	private final static String LevelSigns = "$#^@~";
	private final static String MarkSigns = "!";

	private int LowerLevelsLBound = 0;
	private int LowerLevelsUBound = 0;

	private int HigherLevelsLBound = 0;
	@SuppressWarnings("unused")
	private int HigherLevelsUBound = 0;
	
	private String bookPath;
	private String displayName;
	
	private BookContents bookContents;
	private Book book;

	List<ChapterAddress> flatIndex;
	List<String> rawTextLines;

	
	public BookContents buildBookContents(Book book) 
	{
		bookContents = new BookContents();
		
		this.book = book;
		bookPath = book.getPath();
		
		
		String zipComment = ZipReader.readComment(bookPath);
		
		SimpleSetingsParser Parser = new SimpleSetingsParser(zipComment);
		book.setBookSettingsMap(Parser.buildSettingMap());
		book.setWaevedSources(Parser.buildWeavedDiplaySourcesList());

		displayName = book.getSettings().get("DisplayName");
		
		book.setDisplayName(displayName);
		
		int id = -1;
		try { id = Integer.parseInt(book.getSettings().get("UniqueId")); }
		catch (NumberFormatException e) {}
		
		book.setBookID(id);
		
		buildContents();
		
		return bookContents;
	}

	private void buildContents() 
	{
		readRawText(bookPath);
		
		if (!rawTextLines.isEmpty())
		{
			buildFlatIndex();
			buildChapters();
		}
	}	

	
	//TODO: Add external link reading
	private void buildChapters() 
	{
		TreeNode<IChapter> chapterContentsTree = new TreeNode<IChapter>(null);
		
		TreeNode<IChapter> currentContentsNode = chapterContentsTree;
		
		int currentsLevel = -1;

		String pendingText = "";
		
		ChapterAddress chapid;
		DChapter chap = new DChapter();
		
		for (String line:rawTextLines)
		{
			if (!line.isEmpty())
			{
				char firstChar = line.charAt(0);
				
				int levelCode = LevelSigns.indexOf(firstChar);
				int markCode = MarkSigns.indexOf(firstChar);
				
				if (levelCode != -1 || markCode != -1)
				{
					if (inRange(levelCode, LowerLevelsLBound, LowerLevelsUBound)) 
					{
						//In this case, the level sign is just a marker, NOT part of the hierarchy
						// So we generate a marker from the given line
						pendingText += HtmlMarkupBuilder.genHtmlMarkerComment() + "\n";
						pendingText += HtmlMarkupBuilder.genMarkerAnchor(levelCode + 1, line.replace(firstChar + " ", "")) + "\n";
					}
					else
					{    
						//In this case, we met a '!' marker. The format is: "! {LVL_NAME}\n".
						if (markCode != -1) levelCode = LevelSigns.length() + markCode;
						
						//New level!
						chapid = new ChapterAddress(book.getBookID());
						String title = line.substring(2).trim();
						
						chapid.setTitle(title);
						chapid.setLevel(levelCode);

						
						chap = new DChapter();
						chap.setAddress(chapid);
						
						//Pending text is added right at start of the new chapter
						chap.setChapterText(pendingText);
						pendingText = "";
						
						//Root element
						if (levelCode == 0)
						{
							chapterContentsTree.data = chap;
							
							//Root element has has no address
							chapid.setFullAddress("");
						}
						else
						{
							while (levelCode <= currentsLevel)
							{
								currentContentsNode = currentContentsNode.parent;
								
								if (currentContentsNode == null) break;
								if (currentContentsNode.data == null) break;
								
								currentsLevel = currentContentsNode.data.getChapterAddress().getLevel();
							}
							
							if (currentContentsNode != null)
							{
								//Full address uses parent's address too
								chapid.setFullAddress(currentContentsNode.data.getUID() + " " + title);
								
								currentContentsNode = currentContentsNode.addChild(chap);
							}
						}
						
						currentsLevel = levelCode;
					}
				}
				else if(line.trim() != "") //Ignore empty line totally
				{
					//Simply add this line's text, and any pending text we have
					chap.setChapterText(chap.text() + pendingText + line + "\n");
					
					pendingText = "";
				}
			}
		}

		bookContents.setChapterContentsTree(chapterContentsTree);
		
		bookContents.setFlatIndex(flatIndex);
	}

	//NOTE: If num == UBound this returns False!
	private Boolean inRange(int num, int BoundA, int BoundB)
	{
		int LBound = Math.min(BoundA, BoundB);
		int UBound = Math.max(BoundA, BoundB);
		
		return (num >= LBound && num < UBound);
	}

	
	private void buildFlatIndex()
	{
		Boolean signFound[];
		signFound = new Boolean[LevelSigns.length()];
		for (int i=0; i<LevelSigns.length(); i++) signFound[i] = false;
		
		flatIndex = new ArrayList<ChapterAddress>();
		
		for (String line:rawTextLines)
		{
			if (!line.isEmpty())
			{
				char firstChar = line.charAt(0);
				int levelCode = LevelSigns.indexOf(firstChar);
				
				//Level sign found
				if (levelCode >= 0 && levelCode < LevelSigns.length()) 
				{
					ChapterAddress chapid = new ChapterAddress(book.getBookID());
					chapid.setLevel(levelCode);
					chapid.setTitle(line.replace(firstChar + " ", ""));
					//TODO: Add fullAdress?
					
					flatIndex.add(chapid);
					
					signFound[levelCode] = true;
				}
			}
		}
		
		//Split into 2 level chunks, if these exist:
		
		//Swoop through all zeros 
		//(First one is '$', so we ignore it) 
		int i=1;
		for (; i<LevelSigns.length() && !signFound[i]; i++); LowerLevelsLBound = i;
		
		//Mark all adjacent ones
		for (i=LowerLevelsLBound; i<LevelSigns.length() && signFound[i]; i++); LowerLevelsUBound = i;
		
		//Swoop through zeros again
		for (i=LowerLevelsUBound; i<LevelSigns.length() && !signFound[i]; i++); HigherLevelsLBound = i;
		
		//Mark next bunch of adjacent ones
		for (i=HigherLevelsLBound; i<LevelSigns.length() && signFound[i]; i++); HigherLevelsUBound = i;
	}

	private void readRawText(String filePath) 
	{
		ZipReader zr = new ZipReader(filePath);
		try 
		{
			String rawtext = zr.readContents("BookText");
			
			rawTextLines = Arrays.asList(rawtext.split("\\r?\\n"));
		}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
