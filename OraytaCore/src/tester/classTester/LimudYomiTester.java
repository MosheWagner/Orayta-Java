package tester.classTester;

import book.Book;
import book.contents.IChapter;
import bookBuilder.obk.OBK_Builder;
import bookTree.BookTreeBuilder;
import bookmark.Bookmark;
import bookmark.updatingBookmarks.DafYomiBookmarkGenerator;
import bookmark.updatingBookmarks.HalachaYomitBookmarkBuilder;
import bookmark.updatingBookmarks.MishnaYomitBookmarkBuilder;
import settings.SettingsManager;
import tester.ITest;
import tree.SearchableTree;

public class LimudYomiTester implements ITest
{
	public void Run() 
	{
		SearchableTree<Book> bt = new BookTreeBuilder().buildTree(SettingsManager.getSettings().get_BOOKS_ROOT_DIR());
		
		//Daf yomi
		Bookmark bm = new DafYomiBookmarkGenerator().genBookmark();
		Book masechet = bt.getElementByID(bm.getAddress().getBookID());
		
		OBK_Builder builder = new OBK_Builder();
		masechet.setContents(builder.buildBookContents(masechet));
		
		if (masechet.getContents() != null)
		{
			IChapter chap = masechet.getContents().getChapterByID(bm.getAddress().getUID());
			System.out.println(chap);
		}
		
		//Mishna yomit
		bm = new MishnaYomitBookmarkBuilder(bt).genBookmark();
		
		masechet = bt.getElementByID(bm.getAddress().getBookID());
		masechet.setContents(builder.buildBookContents(masechet));
		if (masechet.getContents() != null)
		{
			IChapter chap = masechet.getContents().getChapterByID(bm.getAddress().getUID());
			System.out.println(chap);
		}
		
		//Halacha yomit
		bm = new HalachaYomitBookmarkBuilder(bt).genBookmark();
		
		masechet = bt.getElementByID(bm.getAddress().getBookID());
		masechet.setContents(builder.buildBookContents(masechet));		
		if (masechet.getContents() != null)
		{
			IChapter chap = masechet.getContents().getChapterByID(bm.getAddress().getUID());
			System.out.println(chap);
		}
	}
}
