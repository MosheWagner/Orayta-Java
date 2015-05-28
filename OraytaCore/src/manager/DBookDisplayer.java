package manager;

import htmlRenderer.HtmlPage;
import htmlRenderer.IHtmlRenderer;
import book.Book;
import book.contents.ChapterAddress;

public class DBookDisplayer implements IBookDisplayManager 
{
	IOraytaCore CoreManager;
	IHtmlRenderer Renderer;
	
	
	public DBookDisplayer(IOraytaCore core)
	{
		CoreManager = core;
		
		if (CoreManager == null) throw new IllegalArgumentException();
		
		Renderer = CoreManager.getHtmlRenderer();
	}
	
	public HtmlPage displayBook(int bookID) 
	{
		Book b = CoreManager.getBookTree().getElementByID(bookID);
		
		return displayBook(b);
	}

	public HtmlPage displayBook(Book book) 
	{
		// TODO Auto-generated method stub
		return null;
	}

	public HtmlPage displayBookAtAddress(ChapterAddress address) 
	{
		// TODO Auto-generated method stub
		return null;
	}

	public Boolean hasNextChapter() {
		// TODO Auto-generated method stub
		return null;
	}

	public Boolean hasPreviousChapter() {
		// TODO Auto-generated method stub
		return null;
	}

	public HtmlPage displayNextChapter() {
		// TODO Auto-generated method stub
		return null;
	}

	public HtmlPage displayPreviousChapter() {
		// TODO Auto-generated method stub
		return null;
	}

	public Book getCurrentlyDisplayedBook() {
		// TODO Auto-generated method stub
		return null;
	}

	public ChapterAddress getCurrentlySetAddress() {
		// TODO Auto-generated method stub
		return null;
	}

	public ChapterAddress getMostAccurateCurrentlyAddress() {
		// TODO Auto-generated method stub
		return null;
	}

	public HtmlPage displayLink(String codedUrl) {
		// TODO Auto-generated method stub
		return null;
	}

}
