package bookmark.updatingBookmarks;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import settings.SettingsManager;
import tree.SearchableTree;
import tree.TreeNode;
import fileManager.SFileReader;
import book.Book;
import book.contents.ChapterAddress;
import bookmark.Bookmark;

public class MishnaYomitBookmarkBuilder implements IBookmarkBuilder
{
	SearchableTree<Book> mBookTree;
	
	public MishnaYomitBookmarkBuilder(SearchableTree<Book> bt)
	{
		mBookTree = bt;
	}
	
	public Bookmark genBookmark() 
	{
		String [] text = readCSVFile();
		String todaysLine = getTodaysLine(text);
		
		String [] todaysLineParts = null;
		
		if (todaysLine != null) todaysLineParts = todaysLine.split(",");
		
		if (todaysLineParts != null && todaysLineParts.length >= 4)
		{
	    	String masechet, perek, mishna;
	        masechet = todaysLineParts[1].split("-")[0];
	        perek = todaysLineParts[2].split("-")[0];
	        mishna = todaysLineParts[3].split("-")[0];
	        
	        Book m = getMasechet(masechet);
	        if (m == null) return null;
	        
	        ChapterAddress addr = genAddress(m, perek, mishna);
	        
	        return new Bookmark(addr, "משנה יומית");
		}
		
		return null;
	}
	
	private Book getMasechet(String masechet) 
	{
        String bookName = ("משנה - " + masechet).trim();
        
        for (TreeNode<Book> tb:mBookTree.getElementsTree())
        {
        	if (tb.data != null && tb.data.getDisplayName() != null)
        	{
        		if (tb.data.getDisplayName().trim().equals(bookName))
        		{
        			return tb.data;
        		}
        	}
        }
        
        return null;
	}
	
	private ChapterAddress genAddress(Book masechet, String perek, String mishna) 
	{
		ChapterAddress addr = new ChapterAddress(masechet.getBookID());
		
		addr.setTitle(" משנה" + mishna);
		
		addr.setFullAddress("פרק " + perek + " - משנה " + mishna);
		
		return addr;
	}

	private String [] readCSVFile()
	{
		try 
		{
			String s = new SFileReader().readContents(SettingsManager.generalSettings().DAILY_LIMUD_FILE_PATH);
			return s.split("\\r?\\n");
		}
		catch (FileNotFoundException e) 
		{
			error();
		} 
		catch (IOException e) 
		{
			error();
		} 
		
		return null;
	}
	
	private String getTodaysLine(String [] csvLines)
	{
		if (csvLines == null) return null;
		
		Date today = new Date();

		DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		
		for (String line:csvLines)
		{
	        String firstPart = line.split(",")[0];
			Date d = null;
			
			try 
			{
				d = format.parse(firstPart);
			} 
			//Ignore invalid lines
			catch (ParseException e) {}
	        
	        if (d != null && DateTools.sameDay(d, today))
	        {
	        	return line;
	        }
		}
		
		return null;
	}
	
	private void error()
	{
		System.out.println("Daily limud list file not found or corrupt!");
	}

}
