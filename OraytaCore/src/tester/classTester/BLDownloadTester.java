package tester.classTester;

import java.util.List;

import download.BooksDownloadManager;
import download.DownloadCategoryTitle;
import download.ICategoryListReadyListener;
import tester.ITest;

public class BLDownloadTester implements ITest
{
	BooksDownloadManager bdm;
	
	public void Run() 
	{
		bdm = new BooksDownloadManager();
		
		bdm.registerToListReadyEvent(new ICategoryListReadyListener() {
			
			public void onCategoryListReady(List<DownloadCategoryTitle> categories) 
			{
				bdm.startCategoryDownload(categories.get(0).getCategoryName());
			}
		});
		
		bdm.downloadBookList();
	}

}
