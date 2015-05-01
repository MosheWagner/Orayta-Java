package tester.classTester;

import book.Book;
import bookTree.BookTreeBuilder;
import bookmark.updatingBookmarks.DafYomiBookmarkGenerator;
import settings.SettingsManager;
import tester.ITest;
import tree.TreeNode;

public class DafYomiTester implements ITest
{
	public void Run() 
	{
		TreeNode<Book> bt = new BookTreeBuilder().buildTree(SettingsManager.generalSettings().BOOKS_ROOT_DIR);
		
		System.out.println(new DafYomiBookmarkGenerator(bt).genBookmark());
		System.out.println(new DafYomiBookmarkGenerator(bt).genBookmark().getAddress());
	}

}
