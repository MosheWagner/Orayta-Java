package bookBuilder;

import java.io.FileNotFoundException;
import java.io.IOException;

import fileManager.SimpleFileReader;
import book.Book;
import book.contents.BookID;


public class FolderBookBuilder implements IBookBuilder
{
	private final static int FOLDER_BOOK_ID = -1;

	public Book buildBook(String path, String displayName)
	{
		Book b = new Book();
		b.setDisplayName(displayName);
		b.setBookID(new BookID(FOLDER_BOOK_ID, displayName));
		
		return b;
	}
	
	public Book buildBook(String folderPath)
	{
		try 
		{
			Book book = new Book();
			
			String s = new SimpleFileReader().readContents(folderPath + ".folder");
			book.parseSttings(s);
			
			String displayName = book.getSettings().get("BranchName");
			book.setDisplayName(displayName);
			
			book.setBookID(new BookID(FOLDER_BOOK_ID, displayName));
			
			return book;
		} 
		//TODO: Better handling
		catch (FileNotFoundException e) {
			System.out.println("Could not find: " + folderPath + ".folder");
			// TODO Auto-generated catch block
			//e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
}
