package tester;

import tester.classTester.*;
import tester.integrationTester.IntegrationTester;

/*
 * This class initiates code tests, as needed while developing.
 *  DON'T USE THIS FOR PRODUCTION  
 */

@SuppressWarnings("unused")
public class TestRunner 
{
	public static void main(String[] args)
	{
		runTest();
	}
	
	private static void runTest()
	{
		//ITest t = new BookTreeTester();
		//ITest t = new BinarySearchTest();
		//ITest t = new OBKReadTest();
		//ITest t = new DecryptTester();
		//ITest t = new CatagoryTitleTester();
		ITest t = new HtmlGenTest();
		//ITest t = new ErrReportTester();
		//ITest t = new HashTester();
		//ITest t = new BLDownloadTester();
		//ITest t = new GematriaTest();
		//ITest t = new LimudYomiTester();
		//ITest t = new BookSettingsTester();
		//ITest t = new BookmarksTester();
		//ITest t = new IntegrationTester();
		
		t.Run();
	}

}
