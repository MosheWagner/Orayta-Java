package bookBuilder;

import java.util.HashMap;
import java.util.Map;

public class SimpleSetingsParser implements ISettingsParser
{

	public Map<String, String> parseSettings(String settingsStr) 
	{
		Map<String, String> map = new HashMap<String, String>();
		for (String line:settingsStr.split("\\r?\\n"))
		{
			String[] lineParts = line.split("=");
			if (lineParts.length == 2)
			{
				map.put(lineParts[0], lineParts[1]);
			}
		}

		return map;
	}

}
