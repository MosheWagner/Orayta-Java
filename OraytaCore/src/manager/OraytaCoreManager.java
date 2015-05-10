package manager;

import book.Book;
import download.DownloadManager;
import settings.DGeneralSettings;
import tree.TreeNode;

/*
 * This class handles everything.
 * It holds the settings, the booklist, 
 */

public abstract class OraytaCoreManager implements UIBridge, IOraytaCore
{
	@SuppressWarnings("unused")
	private DGeneralSettings generalSettings;
	@SuppressWarnings("unused")
	private TreeNode<Book> bookList;
	
	public abstract DownloadManager downloadManager();
}
