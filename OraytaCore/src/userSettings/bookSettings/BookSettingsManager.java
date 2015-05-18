package userSettings.bookSettings;

import java.io.IOException;

import javax.xml.bind.JAXBException;

import settings.SettingsManager;

import xml.ClassIO;

public class BookSettingsManager 
{
	static Boolean initialized = false;
	
	private BookSettingsMapper mBookSettingsMapper = new BookSettingsMapper();
	private ClassIO<BookSettingsMapper> classRW = null;
	
	public BookSettingsMapper getSettingsMapper()
	{
		if (!initialized) initSettings();
		
		return mBookSettingsMapper;
	}
	
	private void initSettings() 
	{
		try 
		{
			classRW = new ClassIO<BookSettingsMapper>(BookSettingsMapper.class);
		} 
		catch (JAXBException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		readSettingsFromFile() ;
		
		
		initialized = true;
	}
	
	public void readSettingsFromFile()
	{
		readSettingsFromFile(SettingsManager.getSettings().get_BOOK_SETTINGS_FILE_PATH());
	}
	
	public void readSettingsFromFile(String path)
	{
		if (classRW != null)
		{
			try 
			{
				mBookSettingsMapper = classRW.readClassFromFile(path);
			} 
			catch (JAXBException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			catch (IOException e) 
			{
				// File not found is fine. Just use defaults
			}
		}
	}
	
	public void saveToFile()
	{
		saveToFile(SettingsManager.getSettings().get_BOOK_SETTINGS_FILE_PATH());
	}
	
	public void saveToFile(String filePath)
	{
		if (classRW != null && mBookSettingsMapper != null)
		{
			try 
			{
				classRW.saveClassToFile(mBookSettingsMapper, filePath);
			} 
			catch (JAXBException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			catch (IOException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
