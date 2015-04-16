package tester.classTester;

import download.DownloadCatagoryTitle;
import tester.ITest;

public class CatagoryTitleTester implements ITest
{
	public void Run() 
	{
		DownloadCatagoryTitle dct = new DownloadCatagoryTitle();
		dct.setCatagoryName("Wombat");
		dct.setSizeInBytes(0.45);
		
		System.out.println(dct);
		
		DownloadCatagoryTitle dctRe = DownloadCatagoryTitle.fromString(dct.toString());
		System.out.println(dctRe);
	}
}
