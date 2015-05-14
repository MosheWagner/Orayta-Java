package tester.classTester;

import java.util.ArrayList;

import tester.ITest;
import userBookSettings.BookSettings;
import userBookSettings.BookSettingsManager;
import userBookSettings.BookSettingsMapper;

public class BookSettingsTester implements ITest 
{

	public void Run() 
	{
		BookSettingsMapper bsm = BookSettingsManager.getSettingsMapper();
		
		for (int i=0; i<4; i++)
		{
			BookSettings bs = new BookSettings();
			
			bs.setShowNikud(i < 2);
			bs.setShowTeamim(i < 2);
			
			ArrayList<Integer> l = new ArrayList<Integer>();
			ArrayList<String> s = new ArrayList<String>();
			for (int j = 0; j<i; j++)
			{
				l.add(j);
				s.add("S" + j);
			}
			
			bs.setWeavedDisplayIDs(l);
			bs.setWeavedDisplayTitles(s);
			
			bsm.put(i, bs);
		}
		bsm.put(4, new BookSettings());
		
		BookSettingsManager.saveToFile();

		BookSettingsManager.readSettingsFromFile();
		BookSettingsMapper bsm2 = BookSettingsManager.getSettingsMapper();
		
		System.out.println(bsm2.get(2).getWeavedDisplayIDs());
		System.out.println(bsm2.get(2).getWeavedDisplayTitles());
		
	}

}
