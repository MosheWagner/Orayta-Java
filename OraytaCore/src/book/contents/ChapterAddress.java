package book.contents;

import tree.IHasID;

public class ChapterAddress implements Comparable<ChapterAddress>, IHasID
{
	private BookID mBookId;
	private String mAddress;
	private int mLevel = -1;
	
	public ChapterAddress(BookID bookId, String id)
	{
		mBookId = bookId;
		setAddress(id);
	}
	
	public ChapterAddress(BookID bookId)
	{
		mBookId = bookId;
	}
	
	public void setAddress(String id) { mAddress = id.trim(); }
	
	public String getUID() {
		return mAddress;
	}
	
	public BookID getBookID() { return mBookId; }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + mBookId.getID();
		result = prime * result + ((mAddress == null) ? 0 : mAddress.hashCode());
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
		if (!mAddress.equals(other.mAddress))
			return false;
		
		return true;
	}
	
	public int hashcode()
	{
		 return mAddress.hashCode();
	}
	
	public String toString()
	{
		 return "(Book: " + mBookId + ") - " + mAddress;
	}

	public int compareTo(ChapterAddress o) 
	{
		return mAddress.compareTo(o.getUID());
	}
	
	public void setLevel(int level) { mLevel = level; }
	
	public int getLevel() { return mLevel; }
}
