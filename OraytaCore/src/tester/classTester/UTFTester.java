package tester.classTester;

import java.io.FileNotFoundException;
import java.io.IOException;

import fileManager.IFileReader;
import fileManager.SimplestFileReader;
import tester.ITest;

public class UTFTester implements ITest
{
	private final static String path = "/home/moshe/Desktop/UTFT.txt";
	
	public void Run() 
	{
		IFileReader r = new SimplestFileReader();
		try {
			System.out.println(r.readContents(path));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
