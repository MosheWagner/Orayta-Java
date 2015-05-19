package classIO;

public interface ISavable <T>
{
	public T getData();
	public void setData(T data);
	
	public void saveData();
	public void reloadData();
	
	public void setFilePath(String path);
}
