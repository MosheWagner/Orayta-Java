package settings;

import javax.xml.bind.annotation.*;

@XmlRootElement
public class DGeneralSettings 
{

	public String BOOKS_ROOT_DIR = "/home/moshe/Orayta/Orayta-Books/books/";
	//TODO: Aren't these the same?
	public String BOOKS_SAVE_PATH = "/home/moshe/Desktop/books/";
	//public String BOOKS_SAVE_PATH = "/home/moshe/Orayta/Orayta-Books/books/";
	
	public String SERVER_BOOK_ROOT_URL = "https://raw.githubusercontent.com/MosheWagner/Orayta-Books/master/books/";
	public String BOOK_LIST_DOWNLOAD_URL = SERVER_BOOK_ROOT_URL + "OraytaBookList";
	
	//public String DAILY_LIMUD_FILE_PATH = BOOKS_ROOT_DIR + "LimudYomi.csv";
	public String DAILY_LIMUD_FILE_PATH = "/home/moshe/Orayta/Orayta-Java/OraytaCore/" + "LimudYomi.csv";
	
	//public String HTML_RENDERED_FILES_PATH = BOOKS_ROOT_DIR + "TMP/";
	public String HTML_RENDERED_FILES_PATH = "/home/moshe/Desktop/";
	
	//public String BOOK_SETTINGS_FILE_PATH = BOOKS_ROOT_DIR + "BooksSettings";
	public String BOOK_SETTINGS_FILE_PATH = "/home/moshe/Desktop/a.map";
	
	public String USER_CSS_FILE_PATH = BOOKS_ROOT_DIR + "../css/user.css";
	
	//public String BOOKMARKS_SAVE_FILE = BOOKS_ROOT_DIR + "Bookmarks";
	public String BOOKMARKS_SAVE_FILE = "/home/moshe/Desktop/bm.save";
	public String LV_BOOKMARKS_SAVE_FILE = "/home/moshe/Desktop/bm2.save";
}
