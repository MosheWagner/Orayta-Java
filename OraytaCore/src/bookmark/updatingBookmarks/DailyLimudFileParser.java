package bookmark.updatingBookmarks;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import settings.SettingsManager;
import fileManager.SFileReader;

public class DailyLimudFileParser 
{
	public static String [] readCSVFile()
	{
		try 
		{
			String s = new SFileReader().readContents(SettingsManager.generalSettings().DAILY_LIMUD_FILE_PATH);
			return s.split("\\r?\\n");
		}
		catch (FileNotFoundException e) 
		{
			error();
		} 
		catch (IOException e) 
		{
			error();
		} 
		
		return null;
	}
	
	public static String getTodaysLine(String [] csvLines)
	{
		if (csvLines == null) return null;
		
		Date today = new Date();

		DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		
		for (String line:csvLines)
		{
	        String firstPart = line.split(",")[0];
			Date d = null;
			
			try 
			{
				d = format.parse(firstPart);
			} 
			//Ignore invalid lines
			catch (ParseException e) {}
	        
	        if (d != null && DateTools.sameDay(d, today))
	        {
	        	return line;
	        }
		}
		
		return null;
	}
	
	private static void error()
	{
		System.out.println("Daily limud list file not found or corrupt!");
	}
}
