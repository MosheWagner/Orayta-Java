package bookBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimpleSetingsParser implements ISettingsParser
{
	String mSettingsStr;
	public SimpleSetingsParser(String settingsStr)
	{
		mSettingsStr = settingsStr;
	}
	
	
	public Map<String, String> buildSettingMap() 
	{
		Map<String, String> map = new HashMap<String, String>();
		for (String line:mSettingsStr.split("\\r?\\n"))
		{
			String[] lineParts = line.split("=");
			if (lineParts.length == 2)
			{
				map.put(lineParts[0], lineParts[1]);
			}
			else if (lineParts.length == 1) // A non valued option is on, so therefore true
			{
				map.put(lineParts[0], "true");
			}
		}
		
		return map;
	}


	public List<String[]> buildWeavedDiplaySourcesList() 
	{
		List<String[]> waevedSourcesList = new ArrayList<String[]> ();
		for (String line:mSettingsStr.split("\\r?\\n"))
		{
			if (line.startsWith("AddSource"))
			{
				String[] lineParts = line.replace("AddSource ", "").split(":");
				if (lineParts.length == 2)
				{
					waevedSourcesList.add(lineParts);
				}
			}
		}

		return waevedSourcesList;
	}

}
