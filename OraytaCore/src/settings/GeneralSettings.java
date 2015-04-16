package settings;

import javax.xml.bind.annotation.*;

@XmlRootElement
public class GeneralSettings 
{
	public String BOOKS_ROOT_DIR = "/home/moshe/Orayta/Orayta-Books/books/";
	public String BOOK_LIST_DOWNLOAD_PATH;
	public String BOOKS_DOWNLOAD_PATH;
}
