package settings;

public class SettingsManager
{
	static Boolean initialized = false;
	
	//private static final String GENERAL_SETTINGS_PATH = "GeneralSettings";
	
	static GeneralSettings mGeneralSettings = new GeneralSettings();
	
	
	public static GeneralSettings generalSettings()
	{
		if (!initialized) initSettings();
		
		return mGeneralSettings;
	}

	private static void initSettings() 
	{
		//TODO: Implement
		
		//initialized = true;
	}
}
