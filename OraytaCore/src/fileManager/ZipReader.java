package fileManager;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ZipReader implements IZipReader
{

	public static String readComment(String zipFilePath)
	{
		ZipFile zipFile;
		try {
			zipFile = new ZipFile(zipFilePath);
			String comment = zipFile.getComment();
			zipFile.close();
			
			return comment;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return "";
		}

	}
	
	public String readContents(String zipPath, String intFileName) throws IOException, FileNotFoundException 
	{
		ZipFile zFile = new ZipFile(zipPath);
		
        ZipEntry entry = zFile.getEntry(intFileName);
        
        InputStream inStream = zFile.getInputStream(entry);
        
        String contents = new StreamReader().readContents(inStream, "utf8");
        zFile.close();
        
        return contents;
	}

}
