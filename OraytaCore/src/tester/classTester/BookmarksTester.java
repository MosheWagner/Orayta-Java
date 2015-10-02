package tester.classTester;

import java.util.List;

import book.Book;
import book.contents.IChapter;
import bookBuilder.obk.OBK_Builder;
import bookTree.BookTree;
import bookTree.BookTreeBuilder;
import bookmark.Bookmark;
import bookmark.BookmarkManager;
import settings.SettingsManager;
import tester.ITest;
import tree.TreeNode;

public class BookmarksTester implements ITest {

	public void Run()
	{
		BookTree bt = new BookTreeBuilder().buildTree(SettingsManager.getSettings().get_BOOKS_ROOT_DIR());
		
		BookmarkManager bmmn = new BookmarkManager(bt);
		List<Bookmark> LimudYomiBMs = bmmn.getLimudYomiBookmarks();
		for(Bookmark bm:LimudYomiBMs)
		{
			bmmn.addUserBookmark(bm);
		}
		
		
		List<Bookmark> BMs = bmmn.getUserSavedBookmarks();
		
		for(Bookmark bm:BMs)
		{
			Book b = bt.getElementByID(bm.getAddress().getBookID());
			b.setContents(new OBK_Builder(b).buildBookContents());
			
			if (b.getContents() != null)
			{
				TreeNode<IChapter> chapNode = b.getContents().getChapterNodeByID(bm.getAddress().getUID());
				System.out.println(chapNode);
			}
			
		}
		
	}

}
