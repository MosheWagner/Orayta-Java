package tester.classTester;

import book.Book;
import book.contents.IChapter;
import bookBuilder.obk.OBK_Builder;
import bookTree.BookTreeBuilder;
import bookmark.Bookmark;
import bookmark.updatingBookmarks.DafYomiBookmarkGenerator;
import bookmark.updatingBookmarks.MishnaYomitBookmarkBuilder;
import settings.SettingsManager;
import tester.ITest;
import tree.SearchableTree;

public class LimudYomiTester implements ITest
{
	public void Run() 
	{
		SearchableTree<Book> bt = new BookTreeBuilder().buildTree(SettingsManager.generalSettings().BOOKS_ROOT_DIR);
		
		//Daf yomi
		Bookmark bm = new DafYomiBookmarkGenerator().genBookmark();
		Book masechet = bt.getElementByID(bm.getAddress().getBookID());
		
		OBK_Builder builder = new OBK_Builder();
		masechet.setContents(builder.buildBookContents(masechet));
		
		if (masechet.getContents() != null)
		{
			IChapter chap = masechet.getContents().getChapterByID(bm.getAddress().getUID()).data;
			System.out.println(chap);
		}
		
		//Mishna yomit
		Bookmark bm2 = new MishnaYomitBookmarkBuilder(bt).genBookmark();
		
		masechet = bt.getElementByID(bm2.getAddress().getBookID());
		masechet.setContents(builder.buildBookContents(masechet));
		if (masechet.getContents() != null)
		{
			IChapter chap = masechet.getContents().getChapterByID(bm2.getAddress().getUID()).data;
			System.out.println(chap);
		}
	}

}
