package tester.classTester;

import download.DownloadCategoryTitle;
import tester.ITest;

public class CatagoryTitleTester implements ITest
{
	public void Run() 
	{
		DownloadCategoryTitle dct = new DownloadCategoryTitle();
		dct.setCategoryName("Wombat");
		dct.setSizeInMB(0.45);
		
		System.out.println(dct);
		
		DownloadCategoryTitle dctRe = DownloadCategoryTitle.fromString(dct.toString());
		System.out.println(dctRe);
	}
}
