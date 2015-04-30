package bookmark;

import book.contents.ChapterAddress;

public class Bookmark 
{
	private ChapterAddress address;
	private String displayName;
	
	public Bookmark(ChapterAddress address, String displayName)
	{
		this.address = address;
		this.displayName = displayName;
	}
	
	public ChapterAddress getAddress() {
		return address;
	}
	public void setAddress(ChapterAddress address) {
		this.address = address;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	
	
}
