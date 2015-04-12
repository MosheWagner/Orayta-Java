package tester;

import tester.classTester.BinarySearchTest;
import tester.classTester.BookTreeTester;
import tester.classTester.OBKReadTest;
import tester.classTester.DecryptTester;


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
		//ITest t = new BinarySearchTest();
		//ITest t = new BookTreeTester();
		//ITest t = new OBKReadTest();
		ITest t = new DecryptTester();
		
		t.Run();
	}

}
