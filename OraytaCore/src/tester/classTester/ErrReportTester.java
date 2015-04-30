package tester.classTester;

import tester.ITest;
import errReport.AutomaticErrorReporter;

public class ErrReportTester implements ITest
{
	
	public void Run() 
	{
		AutomaticErrorReporter repoter = new AutomaticErrorReporter();
		System.out.println(repoter.sendErrorReport("בדיקה","הודעת בדיקה!"));
	}
	
}
