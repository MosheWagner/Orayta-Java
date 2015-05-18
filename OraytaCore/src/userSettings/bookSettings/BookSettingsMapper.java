package userSettings.bookSettings;

import java.util.HashMap;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class BookSettingsMapper
{
	private class BooksSettingsMap extends HashMap<Integer, SingleBookSettings>
	{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public SingleBookSettings put(Integer key, SingleBookSettings value) 
		{
			//Prevent insertions of default values
			if (value.equals(new SingleBookSettings()))
			{
				//Remove the old value, if exists
				if (this.containsKey(key)) this.remove(key);
				
				return null;
			}

			return super.put(key, value);
		}
	}
	
	@XmlElement
	private BooksSettingsMap settingsMap;
	
	public BookSettingsMapper()
	{
		settingsMap = new BooksSettingsMap();
	}
	
	public SingleBookSettings get(Integer id)
	{
		SingleBookSettings bs = settingsMap.get(id);
		if (bs != null) return bs;
		
		return new SingleBookSettings();
	}
	
	public SingleBookSettings put(Integer id, SingleBookSettings settings)
	{
		return settingsMap.put(id, settings);
	}
	
}
