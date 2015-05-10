package tester.classTester;

import java.io.IOException;

import fileManager.FileHash;
import settings.SettingsManager;
import tester.ITest;

public class HashTester implements ITest
{
	
	private final String path = SettingsManager.getSettings().get_BOOKS_ROOT_DIR() + "001_mkra/01_torh/a01_Genesis.obk";
	
	public void Run() 
	{
		try {
			System.out.println(FileHash.calculateMd5(path));
			System.out.println(FileHash.calculateSha1(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
