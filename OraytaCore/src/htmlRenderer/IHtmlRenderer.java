package htmlRenderer;

import java.util.List;

import book.Book;
import book.contents.ChapterAddress;
//import book.contents.IChapter;

public interface IHtmlRenderer 
{
	//public String renderBookChapterIndex(Book book);
	
	public String renderFullBook(Book book);
	public String renderFullBook(Book book, List<Book> otherBooks);
	
	public String renderChapter(Book book, ChapterAddress chapid);
	public String renderChapter(Book book, ChapterAddress chapid, List<Book> otherBooks);
}
