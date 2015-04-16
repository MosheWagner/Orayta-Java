package htmlRenderer;

import java.util.Collection;

import book.Book;
import book.contents.ChapterID;

public interface IHtmlRenderer 
{
	public String renderBookChapterIndex(Book book);
	
	public String renderChapter(Book book, ChapterID chapid);
	public String renderChapterWeaved(Book baseBook, Collection<Book> otherBooks, ChapterID chapid);
}
