package htmlRenderer;

import java.util.Collection;

import book.Book;
import book.contents.ChapterAddress;
//import book.contents.IChapter;

public interface IHtmlRenderer 
{
	public String renderChapterIndex(Book book) ;
	public String renderFullBook(Book book);
	public String renderFullBook(Book book, Collection<Book> otherBooks);
	 
	
	public String renderChapter(Book book, ChapterAddress chapid);
	public String renderChapter(Book book, ChapterAddress chapid, Collection<Book> otherBooks);
}
