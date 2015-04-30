package settings;

import javax.xml.bind.annotation.*;

@XmlRootElement
public class GeneralSettings 
{
	public String BOOKS_ROOT_DIR = "/home/moshe/Orayta/Orayta-Books/books/";
	
	public String BOOK_LIST_DOWNLOAD_URL = "https://raw.githubusercontent.com/MosheWagner/Orayta-Books/master/books/OraytaBookList";
	
	//TODO: Temp!
	public String BOOK_LIST_DOWNLOAD_SAVE_PATH = "/home/moshe/Desktop/BL.txt";
	
	public String BOOKS_DOWNLOAD_PATH;
}
