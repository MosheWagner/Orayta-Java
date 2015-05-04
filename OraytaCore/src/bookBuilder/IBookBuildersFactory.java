package bookBuilder;

import book.Book;

public interface IBookBuildersFactory 
{
	public static final String FOLDER_CONF_SUFFIX = ".folder";
	
	public IBookContentsBuilder getContentsBuilder(Book book);
	public IBookBuilder getBookBuilder(String filePath);
}
