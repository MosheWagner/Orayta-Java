package fileManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import crypt.IZipDecrypter;
import crypt.ZipDecrypter;

import settings.SettingsManager;


public class EncryptedZipReader implements IZipReader
{
	IZipDecrypter decrypter = new ZipDecrypter();
	IZipReader simpleReader = new ZipReader();
	
	public String readContents(String zipPath, String intFileName) throws IOException, FileNotFoundException 
	{
		String DecryptionPass = "ElOB2wAJ!";
		
		String unpackPath = SettingsManager.getSettings().get_HTML_RENDERED_FILES_PATH() + "/unzip.data";
		
		if (!decrypter.decryptZip(zipPath, intFileName, unpackPath, DecryptionPass)) return null;
		
		String contents = simpleReader.readContents(unpackPath, intFileName);
		
		//Remove tmp file
		new File(unpackPath).delete();
		
		return contents; 
	}

}
