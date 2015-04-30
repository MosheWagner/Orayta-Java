package htmlRenderer;

import java.util.Collection;

import book.Book;
import book.contents.ChapterAddress;
//import book.contents.IChapter;

public interface IHtmlRenderer 
{
	public String renderBookChapterIndex(Book book);
	
	public String renderChapter(Book book, ChapterAddress chapid);
	//public String renderChapter(Book book, IChapter chap);
	
	public String renderChapterWeaved(Book baseBook, Collection<Book> otherBooks, ChapterAddress chapid);
}
