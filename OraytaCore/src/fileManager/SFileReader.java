package fileManager;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SFileReader implements IFileReader{

	public String readContents(String filePath) throws IOException, FileNotFoundException 
	{
		byte[] encoded = Files.readAllBytes(Paths.get(filePath));
		return new String(encoded, "utf8");
	}
}
