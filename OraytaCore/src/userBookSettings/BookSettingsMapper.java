package userBookSettings;

import java.util.TreeMap;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class BookSettingsMapper
{
	private class BooksSettingsMap extends TreeMap<Integer, BookSettings>
	{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public BookSettings put(Integer key, BookSettings value) 
		{
			//Prevent insertions of default values
			if (value.equals(new BookSettings()))
			{
				//Remove the old value, if exists
				if (this.containsKey(key)) this.remove(key);
				
				return null;
			}

			return super.put(key, value);
		}
	}
	
	@XmlElement
	private BooksSettingsMap settingsMapper;
	
	public BookSettingsMapper()
	{
		settingsMapper = new BooksSettingsMap();
	}
	
	public BookSettings get(Integer id)
	{
		BookSettings bs = settingsMapper.get(id);
		if (bs != null) return bs;
		
		return new BookSettings();
	}
	
	public BookSettings put(Integer id, BookSettings settings)
	{
		return settingsMapper.put(id, settings);
	}
	
}
