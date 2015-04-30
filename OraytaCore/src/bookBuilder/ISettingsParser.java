package bookBuilder;

import java.util.Map;

public interface ISettingsParser 
{
	Map<String, String> parseSettings(String settingsStr);
}
