package bookmark;

import book.contents.ChapterID;

public class Bookmark 
{
	private ChapterID address;
	private String displayName;
	
	public Bookmark(ChapterID address, String displayName)
	{
		this.address = address;
		this.displayName = displayName;
	}
	
	public ChapterID getAddress() {
		return address;
	}
	public void setAddress(ChapterID address) {
		this.address = address;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	
	
}
