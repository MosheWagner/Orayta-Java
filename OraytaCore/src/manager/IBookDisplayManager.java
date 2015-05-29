package manager;

import java.net.URL;

import book.Book;
import book.contents.ChapterAddress;
import bookmark.Bookmark;

public interface IBookDisplayManager 
{
	public URL displayBook(int bookID);
	public URL displayBook(Book book);
	public URL displayBookAtAddress(Book book, ChapterAddress address);
	public URL displayBookmark(Bookmark bookmark);
	
	public URL displayLink(String codedUrl);
	
	
	public Boolean hasNextChapter();
	public Boolean hasPreviousChapter();
	
	public URL displayNextChapter();
	public URL displayPreviousChapter();
	
	public Book getCurrentlyDisplayedBook();
	public ChapterAddress getCurrentlySetAddress();
	/*
	 * Returns the ChapterAddress the user is currently seeing.
	 * This might be different than the CurrentlySetAddress, as it could be a
	 *  chapter that is a sub chapter of the whole displayed one.
	 */
	public ChapterAddress getMostAccurateCurrentlyAddress();
}
