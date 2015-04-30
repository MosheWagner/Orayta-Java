package settings;

import javax.xml.bind.annotation.*;

@XmlRootElement
public class GeneralSettings 
{
	public String BOOKS_ROOT_DIR = "/home/moshe/Orayta/Orayta-Books/books/";
	
	public String SERVER_BOOK_ROOT_URL = "https://raw.githubusercontent.com/MosheWagner/Orayta-Books/master/books/";
	public String BOOK_LIST_DOWNLOAD_URL = SERVER_BOOK_ROOT_URL + "OraytaBookList";
	
	public String BOOKS_SAVE_PATH = "/home/moshe/Desktop/books/";
	//public String BOOKS_SAVE_PATH = "/home/moshe/Orayta/Orayta-Books/books/";
	
	public String BOOKS_DOWNLOAD_PATH;
}
