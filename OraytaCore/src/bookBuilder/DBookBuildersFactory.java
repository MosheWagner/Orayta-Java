package bookBuilder;

import book.Book;
import bookBuilder.obk.OBK_Builder;

public class DBookBuildersFactory implements IBookBuildersFactory
{
	private static final String OBK_SUFFIX = ".obk";
	
	public IBookContentsBuilder getContentsBuilder(Book book) 
	{
		String path = book.getPath();
		
		if (path.endsWith(OBK_SUFFIX))
		{
			return new OBK_Builder();
		}
		
		return null;
	}

	public IBookBuilder getBookBuilder(String filePath)
	{
		if (filePath.endsWith(FOLDER_CONF_SUFFIX))
		{
			return new FolderBookBuilder();
		}
		else if (filePath.endsWith(OBK_SUFFIX))
		{
			return new BookHeaderBuilder();
		}
		
		return null;
	}

}
