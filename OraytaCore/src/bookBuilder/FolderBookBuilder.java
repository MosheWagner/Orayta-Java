package bookBuilder;

import java.io.FileNotFoundException;
import java.io.IOException;

import fileManager.SFileReader;
import book.Book;
import book.contents.BookID;


public class FolderBookBuilder implements IBookBuilder
{
	private final static int FOLDER_BOOK_ID = -1;

	public Book buildBook(String path, String displayName)
	{
		Book b = new Book();
		b.setPath(path);
		b.setDisplayName(displayName);
		b.setBookID(new BookID(FOLDER_BOOK_ID, displayName));
		
		return b;
	}
	
	public Book buildBook(String folderFilePath)
	{
		try 
		{
			Book book = new Book();
			book.setPath(folderFilePath);
			
			String s = new SFileReader().readContents(folderFilePath);
			book.setBookSettingsMap(new SimpleSetingsParser().parseSettings(s));
			
			String displayName = book.getSettings().get("BranchName");
			book.setDisplayName(displayName);
			
			book.setBookID(new BookID(FOLDER_BOOK_ID, displayName));
			
			return book;
		} 
		//TODO: Better handling
		catch (FileNotFoundException e) 
		{
			System.out.println("Could not find: " + folderFilePath);
			// TODO Auto-generated catch block
			//e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	public Boolean isContainer() 
	{
		return true;
	}
}
