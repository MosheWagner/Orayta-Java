package bookmark;


import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class BookmarkList
{	
	@XmlElement
	private List<Bookmark> Bookmarks = new ArrayList<Bookmark>();

	public List<Bookmark> getBookmarks() {
		return Bookmarks;
	}

	public void set(List<Bookmark> bookmarks) {
		Bookmarks = bookmarks;
	}

	public void add(Bookmark bm) { Bookmarks.add(bm); }
}
