package manager;

import htmlRenderer.IHtmlRenderer;
import htmlRenderer.SHtmlRenderer;
import bookBuilder.DBookBuildersFactory;
import bookBuilder.IBookBuildersFactory;
import bookTree.BookTree;
import bookTree.BookTreeBuilder;
import bookmark.BookmarkManager;
import download.DownloadManager;
import errReport.AutomaticErrorReporter;
import errReport.IErrReporter;
import settings.SettingsManager;
import userSettings.bookSettings.BookSettingsManager;

/*
 * This class handles everything.
 * It holds the settings, the booklist, 
 */

public class OraytaCoreManager implements IOraytaCore
{
	BookSettingsManager mBookSettingsManager;
	BookTree mBookTree;
	DownloadManager mDownloadManager;
	BookmarkManager mBookmarkManager;
	IErrReporter mErrReporter;
	IHtmlRenderer mHtmlRenderer;
	IBookBuildersFactory mBookBuildersFactory;
	
	//Currently using only a single displayer
	IBookDisplayManager mBookDisplayManager;
	
	public void init() 
	{
		mBookTree = buildBookTree();
		
		mBookSettingsManager = new BookSettingsManager();
		mDownloadManager = new DownloadManager();
		mBookmarkManager = new BookmarkManager(mBookTree);
		mErrReporter = new AutomaticErrorReporter();
		mHtmlRenderer = new SHtmlRenderer();
		mBookBuildersFactory = new DBookBuildersFactory();
		
		mBookDisplayManager = buildBookDisplayer();
	}
	

	public void initAsync() 
	{
		// TODO Auto-generated method stub
		
	}

	public void initAsync(ICoreEventsListener listener) {
		// TODO Auto-generated method stub
		
	}
	
	public void registerListener(ICoreEventsListener listener) {
		// TODO Auto-generated method stub
		
	}

	private IBookDisplayManager buildBookDisplayer() 
	{
		return new DBookDisplayer(this);
	}

	private BookTree buildBookTree() {
		BookTreeBuilder treeBuilder = new BookTreeBuilder();
		return treeBuilder.buildTree(SettingsManager.getSettings().get_BOOKS_ROOT_DIR());
	}

	public BookSettingsManager getBookSettingsManager() { return mBookSettingsManager; }
	public BookTree getBookTree() { return mBookTree; }
	public DownloadManager getDownloadManager() { return mDownloadManager; }
	public BookmarkManager getBookmarkManager() { return mBookmarkManager; }
	public IErrReporter getErrReporter() { return mErrReporter; }
	public IHtmlRenderer getHtmlRenderer() { return mHtmlRenderer; }
	public IBookBuildersFactory getBookBuildersFactory() { return mBookBuildersFactory; }
	public IBookDisplayManager getCurrentBookDisplayManager() { return mBookDisplayManager; }

}
