package tester.classTester;

import bookTree.BookTree;
import bookTree.BookTreeBuilder;
import settings.SettingsManager;
import tester.ITest;


public class BookTreeTester  implements ITest {

	public void Run() 
	{
		BookTreeBuilder tb = new BookTreeBuilder();
		BookTree bookTree = tb.buildTree(SettingsManager.generalSettings().BOOKS_ROOT_DIR);
		
		System.out.println(bookTree.getBookTree().printTree());
	}

}
