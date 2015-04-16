package fileManager;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SFileWriter implements IFileWriter
{

	public Boolean writeToFile(String filePath, String str, Boolean overWrite) 
	{
		File f = new File(filePath);
		if (f.exists() && overWrite == false) return false;
		
		BufferedWriter writer = null;
		try
		{
		    writer = new BufferedWriter(new FileWriter(filePath));
		    writer.write(str);

		    writer.close();
		}
		catch ( IOException e)
		{
			//TODO:
			return false;
		}
		
		return true;
	}
}
