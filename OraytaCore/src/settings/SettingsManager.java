package settings;

public class SettingsManager
{
	static Boolean initialized = false;
	
	//private static final String GENERAL_SETTINGS_PATH = "GeneralSettings";
	
	static GeneralSettings mGeneralSettings;
	static UISettings mUISettings;
	
	public static GeneralSettings generalSettings()
	{
		if (!initialized) initSettings();
		
		return mGeneralSettings;
	}
	
	public static UISettings uiSettings()
	{
		if (!initialized) initSettings();
		
		return mUISettings;
	}

	private static void initSettings() 
	{
		//TODO: Implement the right way!!!
		
		
		mGeneralSettings = new GeneralSettings();
		mUISettings = new UISettings();
		
		initialized = true;
	}
}
