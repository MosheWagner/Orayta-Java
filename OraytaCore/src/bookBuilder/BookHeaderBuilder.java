package bookBuilder;

import fileManager.ZipReader;
import book.Book;
import book.contents.BookID;

public class BookHeaderBuilder implements IBookBuilder
{

	public Book buildBook(String path, String displayName) 
	{
		return buildBook(path) ;
	}

	public Book buildBook(String path) 
	{
		Book book = new Book();
		
		book.setPath(path);

		String zipComment = ZipReader.readComment(path);
		
		book.parseSttings(zipComment);

		String displayName = book.getSettings().get("DisplayName");
		book.setDisplayName(displayName);
		
		int id = -1;
		try { id = Integer.parseInt(book.getSettings().get("UniqueId")); }
		catch (NumberFormatException e) {}
		
		book.setBookID(new BookID(id, displayName));
		
		return book;
	}

}
