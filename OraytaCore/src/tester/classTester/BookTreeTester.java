package tester.classTester;

import book.Book;
import bookTree.BookTreeBuilder;
import settings.SettingsManager;
import tester.ITest;
import tree.SearchableTree;


public class BookTreeTester  implements ITest {

	public void Run() 
	{
		BookTreeBuilder tb = new BookTreeBuilder();
		SearchableTree<Book> bookTree = tb.buildTree(SettingsManager.generalSettings().BOOKS_ROOT_DIR);
		
		System.out.println(bookTree.getElementsTree().printTree());
	}

}
