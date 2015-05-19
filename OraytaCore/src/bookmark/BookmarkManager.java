package bookmark;

import java.util.List;

import classIO.SavableClass;

import settings.SettingsManager;

import bookTree.BookTree;
import bookmark.updatingBookmarks.DafYomiBookmarkGenerator;
import bookmark.updatingBookmarks.HalachaYomitBookmarkBuilder;
import bookmark.updatingBookmarks.MishnaYomitBookmarkBuilder;

public class BookmarkManager 
{
	//private List<Bookmark> lastViewedBookmarks;
	private SavableClass<BookmarkList> lastViewedBookmarks;
	private SavableClass<BookmarkList> userSavedBookmarks;
	
	private BookmarkList limudYomiBookmarks = new BookmarkList();
	
	private BookTree mBookTree;
	
	public BookmarkManager(BookTree BookTree)
	{
		mBookTree = BookTree;
		
		genLimudYomiBookmarks();
		
		userSavedBookmarks = new SavableClass<BookmarkList>(BookmarkList.class, SettingsManager.getSettings().get_BOOKMARKS_SAVE_FILE());
		lastViewedBookmarks = new SavableClass<BookmarkList>(BookmarkList.class, SettingsManager.getSettings().get_LV_BOOKMARKS_SAVE_FILE());
	}
	
	public List<Bookmark> getLimudYomiBookmarks() { return limudYomiBookmarks.getBookmarks(); }
	
	
	public void addUserBookmark(Bookmark bookmark) 
	{
		userSavedBookmarks.getData().add(bookmark); 
		//Autosave
		userSavedBookmarks.saveData();
	}
	public void removeUserBookmark(Bookmark bookmark) 
	{
		userSavedBookmarks.getData().remove(bookmark); 
		//Autosave
		userSavedBookmarks.saveData();
	}
	public List<Bookmark> getUserSavedBookmarks() {return userSavedBookmarks.getData().getBookmarks(); }
	

	
	public void addLastViewedBookmark(Bookmark bookmark) 
	{
		lastViewedBookmarks.getData().add(bookmark); 
		//Autosave
		lastViewedBookmarks.saveData();
	}
	public void removeLastViewedBookmark(Bookmark bookmark) 
	{
		lastViewedBookmarks.getData().remove(bookmark); 
		//Autosave
		lastViewedBookmarks.saveData();
	}
	public List<Bookmark> getLastViewedBookmarks() {return lastViewedBookmarks.getData().getBookmarks(); }
	
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
