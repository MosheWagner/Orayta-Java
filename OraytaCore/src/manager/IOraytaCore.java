package manager;

import htmlRenderer.IHtmlRenderer;
import download.DownloadManager;
import errReport.IErrReporter;
import bookBuilder.IBookBuildersFactory;
import bookTree.BookTree;
import bookmark.BookmarkManager;
import userSettings.bookSettings.BookSettingsManager;

/*
 * This interface provides a set on functions that anyone
 *  may expect OraytaCoreManager to implement.
 *  
 * Together with IBookDisplayManager, this includes all OraytaCoreManager publicly does.
 */

public interface IOraytaCore 
{
	//Initialize Orayta. 
	// This should be done in a separate thread, and should later update the listener when it's done.
	public void init();
	public void initAsync();
	public void initAsync(ICoreEventsListener listener);
	
	public BookSettingsManager getBookSettingsManager();
	public BookTree getBookTree();
	public DownloadManager getDownloadManager();
	public BookmarkManager getBookmarkManager();
	public IErrReporter getErrReporter();
	public IHtmlRenderer getHtmlRenderer();
	//public ICSSBuilder getCSSBuilder();
	//TODO: Search manager
	public IBookBuildersFactory getBookBuildersFactory();
	
	public IBookDisplayManager getCurrentBookDisplayManager();
	//public void switchToBookDisplayManager(int index);
	
	//Register listener for events
	public abstract void registerListener(ICoreEventsListener listener);
}
