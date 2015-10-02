package crypt;


public class ZipDecrypter implements IZipDecrypter
{
	public boolean isDecryptPackageInstalled() 
	{
		return Crypter.isKukaytaInstalled();
	}
	
	public boolean decryptZip(String zipFilename, String intFile, String target, String pass) 
	{
		int res =  Crypter.zipDecrypt(zipFilename, intFile,target, pass) ;
		
		if (res == 0) return true;
		else return false;
	}
}
