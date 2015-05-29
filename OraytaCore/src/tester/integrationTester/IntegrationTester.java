package tester.integrationTester;

import manager.IOraytaCore;
import manager.OraytaCoreManager;
import tester.ITest;

public class IntegrationTester implements ITest
{
	IOraytaCore Orayta = new OraytaCoreManager();
	
	public void Run() 
	{
		Orayta.init();
		
		System.out.println(Orayta.getBookTree().getElementsTree().printTree());
		
		System.out.println(Orayta.getCurrentBookDisplayManager().displayBook(1623));
	}

}
