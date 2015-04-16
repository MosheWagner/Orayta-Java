package manager;

import download.DownloadManager;
import book.contents.BookID;
import settings.GeneralSettings;
import tree.TreeNode;

/*
 * This class handles everything.
 * It holds the settings, the booklist, 
 */

public abstract class OraytaCoreManager implements UIBridge, IOraytaCore
{
	@SuppressWarnings("unused")
	private GeneralSettings generalSettings;
	@SuppressWarnings("unused")
	private TreeNode<BookID> bookList;
	
	public abstract DownloadManager downloadManager();
}
