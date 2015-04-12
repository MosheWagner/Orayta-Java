package book.bookTypes;

import java.io.FileNotFoundException;
import java.io.IOException;

import book.contents.BookID;

import fileManager.SimpleFileReader;

import search.ISearchQuery;
import search.ISearchResult;

public class Book_Folder extends ABook
{
	private final static int FOLDER_BOOK_ID = -1;
	
	public Book_Folder(String path)
	{
		mDisplayName = "";
		mFilePath = path;
		mID = new BookID(FOLDER_BOOK_ID, mDisplayName);
		
		buildContents();
	}
	
	public Book_Folder(String path, String displayName)
	{
		mDisplayName = displayName;
		mFilePath = path;
		mID = new BookID(FOLDER_BOOK_ID, mDisplayName);
		
		buildContents();
	}

	@Override
	public void buildContents() {
		// TODO Auto-generated method stub
		try {
			String s = new SimpleFileReader().readContents(mFilePath + ".folder");
			parseSttings(s);
			
			mDisplayName = mBookSettings.get("BranchName");
		} 
		//TODO: Better handling
		catch (FileNotFoundException e) {
			System.out.println("not found: " + mFilePath + ".folder");
			// TODO Auto-generated catch block
			//e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public <Collection> ISearchResult search(ISearchQuery query) {
		// TODO Auto-generated method stub
		return null;
	}


}
