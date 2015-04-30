package fileManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

public class StreamReader 
{
	public String readContents(InputStream inStream, String encoding)
	{
		Scanner scnr = new Scanner(inStream, encoding);
		String contents = scnr.useDelimiter("\\A").next();
		scnr.close();
		
		return contents;
	}
	
	public String readContents(String filePath, String encoding) throws FileNotFoundException
	{
		Scanner scnr = new Scanner( new File(filePath), encoding);
		String contents = scnr.useDelimiter("\\A").next();
		scnr.close();
		
		return contents;
	}
}
