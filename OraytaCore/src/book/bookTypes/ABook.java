package book.bookTypes;

import java.util.HashMap;
import java.util.Map;

import book.contents.BookContents;
import book.contents.BookID;
import book.contents.ChapterID;
import book.contents.IBookMetaData;

import search.ISearchable;
import tree.IHasID;
import tree.TreeNode;

/*
 * THE Book class.
 * From this class you can get a chpter's text (given it's address),
 *  get a tree of the chapters,
 *  and much more.
 */

public abstract class ABook implements Comparable<ABook>, IHasID, ISearchable
{
	protected BookContents mContents = new BookContents();
	protected IBookMetaData metaData;
	protected String mFilePath;
	protected BookID mID;
	protected String mDisplayName;
	protected Map<String, String> mBookSettings;
	//protected String rawText;
	
	//public abstract IChapter getChapter(ChapterID address);
	
	public BookID getBookID() { return mID; }
	public String getUID() { return mID.getUID(); }
	
	public String getPath() { return mFilePath; }
	
	public String getDisplayName() { return mDisplayName; }
	public String toString() {return mDisplayName;}
	
	public TreeNode<ChapterID> getChapterList() {return mContents.getChapterIDTree();}
	
	//protected abstract void parseSttings(String settingsString);
	protected void parseSttings(String settingsString) 
	{
		Map<String, String> map = new HashMap<String, String>();
		for (String line:settingsString.split("\\r?\\n"))
		{
			String[] lineParts = line.split("=");
			if (lineParts.length == 2)
			{
				map.put(lineParts[0], lineParts[1]);
			}
		}

		mBookSettings = map;
	}
	
	public abstract void buildContents();
	
	//Compare by id
	public int compareTo(ABook other) 
	{
		if (this.getBookID() == null) return -1;
		if (other.getBookID() == null) return 1;
		
		return this.getBookID().compareTo(other.getBookID());
	}
}
