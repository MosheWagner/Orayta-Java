package bookmark.updatingBookmarks;

import tree.SearchableTree;
import tree.TreeNode;
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
		String [] text = DailyLimudFileParser.readCSVFile();
		String todaysLine = DailyLimudFileParser.getTodaysLine(text);
		
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


}
