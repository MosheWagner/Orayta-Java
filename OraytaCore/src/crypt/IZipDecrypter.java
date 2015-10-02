package crypt;

public interface IZipDecrypter
{
	public boolean isDecryptPackageInstalled();
	
	public boolean decryptZip(String zipFilename, String intFile, String target, String pass);
}
