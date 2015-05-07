package book.contents;

import tree.IHasID;

public class ChapterAddress implements Comparable<ChapterAddress>, IHasID
{
	private int mBookId;
	private String mTitle;
	private String mFullAddress;
	private int mLevel = -1;
	
	public ChapterAddress(int bookId, String title, String fullAddress)
	{
		mBookId = bookId;
		setTitle(title);
		setFullAddress(fullAddress);
	}
	
	public ChapterAddress(int bookId)
	{
		mBookId = bookId;
	}
	
	public void setTitle(String title) { mTitle = title.trim(); }
	
	public void setFullAddress(String addr) { mFullAddress = addr.trim(); }
	
	
	public String getUID() { return mFullAddress; }
	public String getTitle() { return mTitle; }
	
	public int getBookID() { return mBookId; }
	
	public String toString()
	{
		 return "(Book: " + mBookId + ") - " + mTitle;
	}

	public int compareTo(ChapterAddress o) 
	{
		return mFullAddress.compareTo(o.getUID());
	}
	
	public void setLevel(int level) { mLevel = level; }
	
	public int getLevel() { return mLevel; }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + mBookId;
		result = prime * result
				+ ((mFullAddress == null) ? 0 : mFullAddress.hashCode());
		result = prime * result + mLevel;
		result = prime * result + ((mTitle == null) ? 0 : mTitle.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChapterAddress other = (ChapterAddress) obj;
		if (mBookId != other.mBookId)
			return false;
		if (mFullAddress == null) {
			if (other.mFullAddress != null)
				return false;
		} else if (!mFullAddress.equals(other.mFullAddress))
			return false;
		if (mLevel != other.mLevel)
			return false;
		if (mTitle == null) {
			if (other.mTitle != null)
				return false;
		} else if (!mTitle.equals(other.mTitle))
			return false;
		return true;
	}

}
