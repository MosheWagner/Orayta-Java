package fileManager;

public interface IFileWriter 
{
	public Boolean writeToFile(String filePath, String str, Boolean overWrite);
}
