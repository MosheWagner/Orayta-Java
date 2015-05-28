package userSettings.bookSettings;

import classIO.SavableClass;
import settings.SettingsManager;

public class BookSettingsManager extends SavableClass<BookSettingsMapper>
{
	public BookSettingsManager() 
	{
		super(BookSettingsMapper.class, SettingsManager.getSettings().get_BOOK_SETTINGS_FILE_PATH());
	}
	
	private BookSettingsManager(Class<BookSettingsMapper> typeParameterClass, String savePath) 
	{
		super(typeParameterClass, savePath);
	}
}
