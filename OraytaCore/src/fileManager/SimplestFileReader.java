package fileManager;

import java.io.FileNotFoundException;
import java.io.IOException;

public class SimplestFileReader implements IFileReader{

	public String readContents(String filePath) throws IOException, FileNotFoundException
	{
		return new StreamReader().readContents(filePath, "utf8");
	}

}
