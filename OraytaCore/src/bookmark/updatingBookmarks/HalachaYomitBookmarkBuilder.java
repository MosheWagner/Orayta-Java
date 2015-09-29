package bookmark.updatingBookmarks;

import fileManager.DatedCSVFileParser;
import hebrewStuff.GematriaTools;

import java.util.Arrays;
import java.util.List;

import settings.SettingsManager;

import book.Book;
import book.contents.ChapterAddress;
import bookTree.BookTree;
import bookmark.Bookmark;

public class HalachaYomitBookmarkBuilder implements IBookmarkBuilder 
{
	BookTree mBootTree;
	
	public HalachaYomitBookmarkBuilder(BookTree bt) 
	{
		mBootTree = bt;
	}

	public Bookmark genBookmark() 
	{
		DatedCSVFileParser parser = new DatedCSVFileParser();
		parser.readFile(SettingsManager.getSettings().get_DAILY_LIMUD_FILE_PATH());
		String todaysLine = parser.getTodaysLine();
		
		String [] todaysLineParts = null;
		
		if (todaysLine != null) todaysLineParts = todaysLine.split(",");
		
		if (todaysLineParts != null && todaysLineParts.length >= 6)
		{
	    	String siman, seif;
	    	siman = todaysLineParts[4].split("-")[0];
	    	seif = todaysLineParts[5].split("-")[0];
	        
	        Book p = getShulchanAruchPart(siman);
	        if (p == null) return null;
	        
	        ChapterAddress addr = genAddress(p, siman, seif);
	        
	        return new Bookmark(addr, "הלכה יומית");
		}
		
		return null;
	}

	private ChapterAddress genAddress(Book p, String siman, String seif)
	{
		ChapterAddress addr = new ChapterAddress(p.getBookID());
		
		addr.setFullAddress("סימן " + siman + " {" + seif + "}");
		addr.setTitle(siman + " {" + seif + "}");
		
		addr.setTitle(siman);
		return addr;
	}

	private static final List <Integer> PARTS_START_SIMAN = Arrays.asList(127, 241, 344, 428, 529, 697);
	private static final int BASE_SHULCHAN_ARUCH_BOOK_ID = 80000; //Id of Shulchan aruch part 1
	private Book getShulchanAruchPart(String siman) 
	{
		int sim = GematriaTools.gematriaValue(siman);
		
        //Find which part of Shulchan aruch we are in
        int id = 0;
        for (; PARTS_START_SIMAN.get(id) < sim; id++);
        id += BASE_SHULCHAN_ARUCH_BOOK_ID;
        
        return mBootTree.getElementByID(id);
	}

}
