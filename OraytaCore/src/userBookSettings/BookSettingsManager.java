package userBookSettings;

import java.io.IOException;

import javax.xml.bind.JAXBException;

import settings.SettingsManager;

import xml.ClassIO;

public class BookSettingsManager 
{
	static Boolean initialized = false;
	
	private static BookSettingsMapper mBookSettingsMapper = new BookSettingsMapper();
	private static ClassIO<BookSettingsMapper> classRW = null;
	
	public static BookSettingsMapper getSettingsMapper()
	{
		if (!initialized) initSettings();
		
		return mBookSettingsMapper;
	}
	
	private static void initSettings() 
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
	
	public static void readSettingsFromFile()
	{
		readSettingsFromFile(SettingsManager.getSettings().get_BOOK_SETTINGS_FILE_PATH());
	}
	
	public static void readSettingsFromFile(String path)
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
				// File not found is fine
			}
		}
	}
	
	public static void saveToFile()
	{
		saveToFile(SettingsManager.getSettings().get_BOOK_SETTINGS_FILE_PATH());
	}
	
	public static void saveToFile(String filePath)
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
