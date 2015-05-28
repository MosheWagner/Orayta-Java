package manager;

import htmlRenderer.HtmlPage;
import book.Book;
import book.contents.ChapterAddress;

public interface IBookDisplayManager 
{
	public HtmlPage displayBook(int bookID);
	public HtmlPage displayBook(Book book);
	public HtmlPage displayBookAtAddress(ChapterAddress address);
	
	public HtmlPage displayLink(String codedUrl);
	
	
	public Boolean hasNextChapter();
	public Boolean hasPreviousChapter();
	
	public HtmlPage displayNextChapter();
	public HtmlPage displayPreviousChapter();
	
	public Book getCurrentlyDisplayedBook();
	public ChapterAddress getCurrentlySetAddress();
	/*
	 * Returns the ChapterAddress the user is currently seeing.
	 * This might be different than the CurrentlySetAddress, as it could be a
	 *  chapter that is a sub chapter of the whole displayed one.
	 */
	public ChapterAddress getMostAccurateCurrentlyAddress();
}
