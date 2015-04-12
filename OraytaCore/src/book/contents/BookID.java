package book.contents;

import tree.IHasID;

public class BookID implements Comparable<BookID>, IHasID
{
	Integer UID = -1;
	String mDisplayName;

	public BookID(int id, String displayName)
	{
		UID = id;
		mDisplayName = displayName;
	}
	
	public String getDisplayName() { return mDisplayName; }
	
	public int getID() { return UID; }
	
	public String getUID() { return Integer.toString(UID); }
	
	public int compareTo(BookID o) {
		return UID.compareTo(o.getID());
	}
	
	public String toString() {
		return getDisplayName();
	}

	@Override
	public int hashCode() {
		return UID.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BookID other = (BookID) obj;
		if (UID != other.UID)
			return false;
		return true;
	}
}
