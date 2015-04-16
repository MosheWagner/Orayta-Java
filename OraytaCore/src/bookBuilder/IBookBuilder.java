package bookBuilder;

import book.Book;

public interface IBookBuilder 
{
	public Book buildBook(String path, String displayName);
	public Book buildBook(String path);
}
