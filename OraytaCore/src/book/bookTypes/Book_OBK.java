package book.bookTypes;

import html.HtmlBuilder;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import book.contents.BookID;
import book.contents.ChapterID;
import book.contents.DChapter;
import book.contents.IChapter;
import fileManager.ZipReader;
import search.ISearchQuery;
import search.ISearchResult;
import tree.TreeNode;

public class Book_OBK extends ABook
{
	private final static String LevelSigns = "$#^@~";
	private final static String MarkSigns = "!";

	private int LowerLevelsLBound = 0;
	private int LowerLevelsUBound = 0;

	private int HigherLevelsLBound = 0;
	private int HigherLevelsUBound = 0;

	List<ChapterID> flatIndex;
	List<String> rawTextLines;
	
	public Book_OBK(String path)
	{
		mFilePath = path ;

		String zipComment = ZipReader.readComment(mFilePath);
		
		parseSttings(zipComment);

		mDisplayName = mBookSettings.get("DisplayName");
		
		int id = -1;
		try { id = Integer.parseInt(mBookSettings.get("UniqueId")); }
		catch (NumberFormatException e) {}
		
		mID = new BookID(id, mDisplayName);
	}
	
	

	@Override
	public void buildContents() 
	{
		readRawText();
		
		if (!rawTextLines.isEmpty())
		{
			buildFlatIndex();
			buildChapters();
		}
	}	

	private void buildChapters() 
	{
		TreeNode<IChapter> chapterContentsTree = new TreeNode<IChapter>(null);
		TreeNode<ChapterID> chapterIDTree = new TreeNode<ChapterID>(null);
		
		TreeNode<IChapter> currentContentsNode = chapterContentsTree;
		TreeNode<ChapterID> currentIDNode = chapterIDTree;
		
		int currentsLevel = -1;

		ChapterID chapid;
		DChapter chap = new DChapter();
		
		for (String line:rawTextLines)
		{
			if (!line.isEmpty())
			{
				char firstChar = line.charAt(0);
				
				int levelCode = LevelSigns.indexOf(firstChar);
				int markCode = MarkSigns.indexOf(firstChar);
				
				if (levelCode != -1)
				{
					if (inRange(levelCode, LowerLevelsLBound, LowerLevelsUBound)) 
					{
						//In this case, the level sign is just a marker, NOT part of the hierarchy
						// So we generate a marker from the given line
						chap.setChapterText(chap.text() + genMarkerAnchor(levelCode, line.replace(firstChar + " ", "")) + "\n");
					}
					else
					{    
						//New level!
						chapid = new ChapterID(this.getBookID());
						String title = line.substring(2);
						chapid.setID(title);
						chapid.setLevel(levelCode);
						
						chap = new DChapter();
						chap.setAddress(chapid);
						
						//Root element
						if (levelCode == 0)
						{
							chapterContentsTree.data = chap;
							chapterIDTree.data = chapid;
						}
						else
						{
							while (levelCode <= currentsLevel)
							{
								currentContentsNode = currentContentsNode.parent;
								currentIDNode = currentIDNode.parent;
								
								if (currentContentsNode == null || currentIDNode == null) break;
								if (currentContentsNode.data == null) break;
								
								currentsLevel = currentContentsNode.data.getChapterAddress().getLevel();
							}
							
							if (currentContentsNode != null && currentIDNode != null)
							{
								currentContentsNode = currentContentsNode.addChild(chap);
								currentIDNode = currentIDNode.addChild(chapid);
							}
						}
						
						currentsLevel = levelCode;
					}
				}
				else if(markCode != -1)
				{
					//In this case, we met a '!' marker. The format is: "! {LVL_NAME}\n".
					// So we need to crop the "! {" and the "}" to get the LVL_NAME
					String markerText = line.replace("! {", "");
					markerText = markerText.replace("}", "");
					
					chap.setChapterText(chap.text() + genAnchor(markerText) + "\n");
					
					System.out.println(genAnchor(markerText));
				}
				else
				{
					//Just more text
					chap.setChapterText(chap.text() + line + "\n");
				}
			}
		}

		mContents.setChapterContentsTree(chapterContentsTree);
		mContents.setChapterIDTree(chapterIDTree);
	}

	private String genAnchor(String markerText)
	{
		return HtmlBuilder.createAnchor(markerText);
	}
	
	private String genMarkerAnchor(int levelCode, String markerText)
	{
		return HtmlBuilder.createHeading(levelCode, genAnchor(markerText));
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
		
		flatIndex = new ArrayList<ChapterID>();
		
		for (String line:rawTextLines)
		{
			if (!line.isEmpty())
			{
				char firstChar = line.charAt(0);
				int levelCode = LevelSigns.indexOf(firstChar);
				
				//Level sign found
				if (levelCode >= 0 && levelCode < LevelSigns.length()) 
				{
					ChapterID chapid = new ChapterID(this.getBookID());
					chapid.setLevel(levelCode);
					chapid.setID(line.replace(firstChar + " ", ""));
					
					flatIndex.add(chapid);
					
					signFound[levelCode] = true;
				}
			}
		}
		
		//for (ChapterID cid:flatIndex)
		//{
		//	System.out.print(cid.getLevel() + " : ");
		//	System.out.println(cid);
		//}
		
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

	private void readRawText() 
	{
		ZipReader zr = new ZipReader(mFilePath);
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

	public <Collection> ISearchResult search(ISearchQuery query) {
		// TODO Auto-generated method stub
		return null;
	}

}
