package classIO;

import java.io.IOException;

import javax.xml.bind.JAXBException;

public class SavableClass<T> implements ISavable<T>
{
	ClassIO<T> classIO = null;
	T mData = null;
	
	String filePath = null;
	
	Class<T> mClassType;

	public SavableClass(Class<T> typeParameterClass, String savePath)
	{
		filePath = savePath;
		
		mClassType = typeParameterClass;
		mData = newInstance();
		
		try 
		{
			classIO = new ClassIO<T>(typeParameterClass);
		} 
		catch (JAXBException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		readFromFile();
	}
	
	private T newInstance()
	{
		try 
		{
			return mClassType.newInstance();
		} 
		catch (InstantiationException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (IllegalAccessException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public T getData() { return mData; }

	public void setData(T data) { mData = data; }

	public void saveData() 
	{
		if (filePath != null && classIO != null && mData != null)
		{
			try 
			{
				classIO.saveClassToFile(mData, filePath);
			} 
			catch (JAXBException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			catch (IOException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void reloadData() { readFromFile(); }

	private void readFromFile()
	{
		if (classIO != null)
		{
			try 
			{
				mData = classIO.readClassFromFile(filePath);
			} 
			catch (JAXBException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			catch (IOException e) 
			{
				// File not found is fine. Just use defaults
				mData = newInstance();
			}
		}
	}
	
	public void setFilePath(String path) 
	{ 
		filePath = path; 
	}

}
