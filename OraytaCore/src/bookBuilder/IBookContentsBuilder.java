package bookBuilder;

import book.Book;
import book.contents.BookContents;

public interface IBookContentsBuilder 
{
	public BookContents buildBookContents(Book book);
}
