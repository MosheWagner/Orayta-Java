package fileManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class FileHash 
{
	private static String calculateHash(File file, String algorithmName) throws IOException, NoSuchAlgorithmException 
	{
	    MessageDigest digest = MessageDigest.getInstance(algorithmName);
	    InputStream fis = new FileInputStream(file);
	    int n = 0;
	    byte[] buffer = new byte[8192];
	    while (n != -1) {
	        n = fis.read(buffer);
	        if (n > 0) {
	            digest.update(buffer, 0, n);
	        }
	    }
	    
	    fis.close();
	    
	    //convert the byte to hex format
	    byte[] mdbytes = digest.digest();
	    
	    StringBuffer sb = new StringBuffer("");
	    for (int i = 0; i < mdbytes.length; i++) {
	    	sb.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16).substring(1));
	    }
	    
	    return sb.toString();
	}
	
	public static String calculateSha1(String filePath) throws IOException
	{
		return calculateSha1(new File(filePath));
	}
	
	public static String calculateSha1(File file) throws IOException
	{
		try {
			return calculateHash(file, "SHA-1");
		} 
		// NOTE: This should never happen!
		catch (NoSuchAlgorithmException e) { return null;}
	}
	
	public static String calculateMd5(String filePath) throws IOException
	{
		return calculateMd5(new File(filePath));
	}
	
	public static String calculateMd5(File file) throws IOException
	{
		try {
			return calculateHash(file, "MD5");
		} 
		// NOTE: This should never happen!
		catch (NoSuchAlgorithmException e) { return null;}
	}

}
