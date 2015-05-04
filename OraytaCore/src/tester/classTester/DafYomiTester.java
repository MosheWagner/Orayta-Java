package tester.classTester;

import book.Book;
import book.contents.IChapter;
import bookBuilder.obk.OBK_Builder;
import bookTree.BookTree;
import bookTree.BookTreeBuilder;
import bookmark.Bookmark;
import bookmark.updatingBookmarks.DafYomiBookmarkGenerator;
import settings.SettingsManager;
import tester.ITest;

public class DafYomiTester implements ITest
{
	public void Run() 
	{
		BookTree bt = new BookTreeBuilder().buildTree(SettingsManager.generalSettings().BOOKS_ROOT_DIR);
		
		
		Bookmark bm = new DafYomiBookmarkGenerator().genBookmark();
		Book masechet = bt.getBook(bm.getAddress().getBookID());
		
		OBK_Builder builder = new OBK_Builder();
		masechet.setContents(builder.buildBookContents(masechet));
		
		if (masechet.getContents() != null)
		{
			IChapter chap = masechet.getContents().getChapterByID(bm.getAddress().getUID()).data;
			System.out.println(chap);
		}
	}

}
