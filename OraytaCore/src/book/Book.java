package book;

import java.util.HashMap;
import java.util.Map;

import book.contents.BookContents;
import book.contents.BookID;
import book.contents.ChapterID;
import book.contents.IBookMetaData;

import search.IPuretextSearchable;
import search.chapterMapping.ISortedNumberList;
import tree.IHasID;
import tree.TreeNode;

/*
 * THE Book class.
 * From this class you can get a chpter's text (given it's address),
 *  get a tree of the chapters,
 *  and much more.
 */

public class Book implements Comparable<Book>, IHasID, IPuretextSearchable
{
	protected BookContents mContents;
	protected IBookMetaData metaData;
	protected String mFilePath;
	
	protected BookID mID;
	protected String mDisplayName;
	protected Map<String, String> mBookSettingsMap;

	//public abstract IChapter getChapter(ChapterID address);
	
	public BookID getBookID() { return mID; }
	public void setBookID(BookID id) { mID=id; }
	
	public String getUID() { return mID.getUID(); }
	
	public String getPath() { return mFilePath; }
	public void setPath(String filePath) { mFilePath=filePath; }
	
	public String getDisplayName() { return mDisplayName; }
	public void setDisplayName(String dispName) { mDisplayName = dispName; }
	
	public void setContents(BookContents bookContents) {mContents = bookContents; }
	public BookContents getContents() {return mContents; }

	public String toString() {return mDisplayName;}
	
	public TreeNode<ChapterID> getChapterIDList() {return mContents.getChapterIDTree();}
	
	public Map<String, String> getSettings() { return mBookSettingsMap; }
	
	public void parseSttings(String settingsString) 
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

		mBookSettingsMap = map;
	}
	
	//Compare by id
	public int compareTo(Book other) 
	{
		if (this.getBookID() == null) return -1;
		if (other.getBookID() == null) return 1;
		
		return this.getBookID().compareTo(other.getBookID());
	}
	
	
	public void buildSearchIndex() {
		// TODO Auto-generated method stub
		
	}
	public String getPureText() {
		// TODO Auto-generated method stub
		return null;
	}
	public ISortedNumberList getChapterMap() {
		// TODO Auto-generated method stub
		return null;
	}

}
