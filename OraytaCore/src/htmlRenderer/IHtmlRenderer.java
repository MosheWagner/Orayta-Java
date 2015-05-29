package htmlRenderer;

import java.net.URL;
import java.util.Collection;

import book.Book;
import book.contents.ChapterAddress;
//import book.contents.IChapter;

public interface IHtmlRenderer 
{
	public URL renderChapterIndex(Book book) ;
	public URL renderFullBook(Book book);
	public URL renderFullBook(Book book, Collection<Book> otherBooks);
	 
	public URL renderChapter(Book book, ChapterAddress chapid);
	public URL renderChapter(Book book, ChapterAddress chapid, Collection<Book> otherBooks);
}
