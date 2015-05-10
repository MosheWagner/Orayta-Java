package tester.classTester;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.bind.JAXBException;

import tester.ITest;
import userBookSettings.BookSettings;
import userBookSettings.BookSettingsMapper;
import xml.ClassIO;

public class BookSettingsTester implements ITest 
{

	public void Run() 
	{
		BookSettingsMapper bsm = new BookSettingsMapper();
		
		for (int i=0; i<4; i++)
		{
			BookSettings bs = new BookSettings();
			
			bs.setShowNikud(i > 2);
			bs.setShowTeamim(i < 2);
			
			ArrayList<String> l = new ArrayList<String>();
			for (int j = 0; j<i; j++) l.add(String.valueOf(j));
			
			bs.setShowWeavedDisplay(l);
			
			bsm.getSettingsMapper().put(i, bs);
		}
		try {
			ClassIO<BookSettingsMapper> mapperIO = new ClassIO<BookSettingsMapper>(BookSettingsMapper.class);
			mapperIO.saveClassToFile(bsm, "/home/moshe/Desktop/a.map");
			
			//System.out.println(mapperIO.classToString(bsm));
			BookSettingsMapper bsm2 = mapperIO.readClassFromFile("/home/moshe/Desktop/a.map");
			
			System.out.println(bsm2.getSettingsMapper().get(2).getShowWeavedDisplay());
			
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
