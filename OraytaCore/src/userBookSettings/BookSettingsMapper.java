package userBookSettings;

import java.util.Map;
import java.util.TreeMap;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class BookSettingsMapper 
{
	@XmlElement
	private Map<Integer, BookSettings> settingsMapper;
	
	public BookSettingsMapper()
	{
		settingsMapper = new TreeMap<Integer, BookSettings>();
	}
	
	public Map<Integer, BookSettings> getSettingsMapper() 
	{
		return settingsMapper;
	}
	
}
