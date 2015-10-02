package crypt;

public class SimpleZofenProvider implements IZofenProvider
{	
	String magicString = "####";
	
	public String getZofen() 
	{
		return magicString;
	}

	public boolean isProviderAvailable() 
	{
		return true;
	}
}
