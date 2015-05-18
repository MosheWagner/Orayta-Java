package bookmark;

import java.io.IOException;
import java.util.List;

import javax.xml.bind.JAXBException;

import settings.SettingsManager;
import xml.ClassIO;

import bookTree.BookTree;
import bookmark.updatingBookmarks.DafYomiBookmarkGenerator;
import bookmark.updatingBookmarks.HalachaYomitBookmarkBuilder;
import bookmark.updatingBookmarks.MishnaYomitBookmarkBuilder;

public class BookmarkManager 
{
	private ClassIO<BookmarkList> classRW = null;
	
	//private List<Bookmark> lastViewedBookmarks;
	private BookmarkList userSavedBookmarks = new BookmarkList();
	private BookmarkList limudYomiBookmarks = new BookmarkList();
	
	private BookTree mBookTree;
	
	public BookmarkManager(BookTree BookTree)
	{
		mBookTree = BookTree;
		
		genLimudYomiBookmarks();
		
		try 
		{
			classRW = new ClassIO<BookmarkList>(BookmarkList.class);
		} 
		catch (JAXBException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		readSavedBookmarksFromFile();
	}
	
	public List<Bookmark> getLimudYomiBookmarks() { return limudYomiBookmarks.getBookmarks(); }
	
	public List<Bookmark> getSavedBookmarks() {return userSavedBookmarks.getBookmarks(); }
	public void addBookmark(Bookmark bookmark) {userSavedBookmarks.add(bookmark); }
	
	private void readSavedBookmarksFromFile()
	{
		readSavedBookmarksFromFile(SettingsManager.getSettings().get_BOOKMARKS_SAVE_FILE());
	}
	
	public void readSavedBookmarksFromFile(String filePath)
	{
		if (classRW != null)
		{
			try 
			{
				userSavedBookmarks = classRW.readClassFromFile(filePath);
			} 
			catch (JAXBException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			catch (IOException e) 
			{
				// File not found is fine. Just use defaults
			}
		}
	}
	
	public void saveBookmarksToFile()
	{
		saveBookmarksToFile(SettingsManager.getSettings().get_BOOKMARKS_SAVE_FILE());
	}
	
	public void saveBookmarksToFile(String filePath)
	{
		try 
		{
			classRW.saveClassToFile(userSavedBookmarks, filePath);
		} 
		catch (JAXBException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void genLimudYomiBookmarks()
	{
		//Daf yomi
		Bookmark DafYomi = new DafYomiBookmarkGenerator().genBookmark();
		limudYomiBookmarks.add(DafYomi);
		
		//Mishna yomit
		Bookmark MishnaYomit = new MishnaYomitBookmarkBuilder(mBookTree).genBookmark();
		limudYomiBookmarks.add(MishnaYomit);
		
		//Halacha yomit
		Bookmark HalachaYomit = new HalachaYomitBookmarkBuilder(mBookTree).genBookmark();
		limudYomiBookmarks.add(HalachaYomit);
	}


}
