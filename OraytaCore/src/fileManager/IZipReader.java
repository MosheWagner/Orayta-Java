package fileManager;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface IZipReader 
{
	public String readContents(String zipPath, String intFileName) throws IOException, FileNotFoundException;
}
