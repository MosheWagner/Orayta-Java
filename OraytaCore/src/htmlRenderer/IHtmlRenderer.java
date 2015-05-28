package htmlRenderer;

import java.util.Collection;

import book.Book;
import book.contents.ChapterAddress;
//import book.contents.IChapter;

public interface IHtmlRenderer 
{
	public HtmlPage renderChapterIndex(Book book) ;
	public HtmlPage renderFullBook(Book book);
	public HtmlPage renderFullBook(Book book, Collection<Book> otherBooks);
	 
	
	public HtmlPage renderChapter(Book book, ChapterAddress chapid);
	public HtmlPage renderChapter(Book book, ChapterAddress chapid, Collection<Book> otherBooks);
}
