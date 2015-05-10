package settings;

public class SettingsManager
{
	static Boolean initialized = false;
	static DSettingsHolder mDSettingsHolder;

	public static ISettingsHolder getSettings()
	{
		if (!initialized) initSettings();
		
		return mDSettingsHolder;
	}
	
	private static void initSettings() 
	{
		mDSettingsHolder = new DSettingsHolder();
		
		initialized = true;
	}
}
