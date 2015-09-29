package fileManager;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import bookmark.updatingBookmarks.DateTools;


public class DatedCSVFileParser 
{
	private String [] csvLines;
	
	public void readFile(String filePath)
	{
		try 
		{
			String s = new SFileReader().readContents(filePath);
			csvLines = s.split("\\r?\\n");
		}
		catch (FileNotFoundException e) 
		{
			error();
		} 
		catch (IOException e) 
		{
			error();
		} 
	}
	
	public String getTodaysLine()
	{
		if (csvLines == null) return null;
		
		Date today = new Date();

		DateFormat format = new SimpleDateFormat("dd/MM/yy");
		
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
	
	private void error()
	{
		System.out.println("Daily limud list file not found or corrupt!");
	}
}
