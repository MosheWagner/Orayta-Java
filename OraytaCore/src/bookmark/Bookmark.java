package bookmark;

import javax.xml.bind.annotation.XmlRootElement;

import book.contents.ChapterAddress;

@XmlRootElement
public class Bookmark 
{
	private ChapterAddress address;
	private String displayName;
	
	public Bookmark() { }
	
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result
				+ ((displayName == null) ? 0 : displayName.hashCode());
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
		Bookmark other = (Bookmark) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (displayName == null) {
			if (other.displayName != null)
				return false;
		} else if (!displayName.equals(other.displayName))
			return false;
		return true;
	}
	
	
}
