package tester.classTester;

import download.BooksDownloadManager;
import tester.ITest;

public class BLDownloadTester implements ITest{

	public void Run() 
	{
		BooksDownloadManager bdm = new BooksDownloadManager();
		
		bdm.downloadBookList();
	}

}
