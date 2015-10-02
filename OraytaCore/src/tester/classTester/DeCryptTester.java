package tester.classTester;

import tester.ITest;

public class DeCryptTester implements ITest
{
	int ID = 1001; 

	ITest gen = new HtmlGenTest(ID, "");
	
	public void Run() 
	{
		gen.Run();
	}
	
}
