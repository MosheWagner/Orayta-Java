package settings;

import java.util.Arrays;
import java.util.List;

public class DSettingsHolder implements ISettingsHolder 
{
	static DGeneralSettings mGeneralSettings;
	static DUISettings mUISettings;
	
	public DSettingsHolder()
	{
		//TODO: Implement the right way!!!
		
		mGeneralSettings = new DGeneralSettings();
		mUISettings = new DUISettings();
	}
	
	
	public String get_BOOKS_ROOT_DIR() {
		return mGeneralSettings.BOOKS_ROOT_DIR;
	}

	public String get_SERVER_BOOK_ROOT_URL() {
		return mGeneralSettings.SERVER_BOOK_ROOT_URL;
	}

	public String get_BOOK_LIST_DOWNLOAD_URL() {
		return mGeneralSettings.BOOK_LIST_DOWNLOAD_URL;
	}

	public String get_BOOKS_SAVE_PATH() {
		return mGeneralSettings.BOOKS_SAVE_PATH;
	}

	public String get_DAILY_LIMUD_FILE_PATH() {
		return mGeneralSettings.DAILY_LIMUD_FILE_PATH;
	}

	public String get_USER_CSS_FILE_PATH() {
		return mGeneralSettings.USER_CSS_FILE_PATH;
	}

	public String get_FONT_NAME() {
		return mUISettings.FONT_NAME;
	}

	public int get_BASE_FONT_SIZE() {
		return mUISettings.BASE_FONT_SIZE;
	}

	public List<String> get_WEAVED_DISPLAY_COLOR_LIST() {
		return Arrays.asList(mUISettings.WEAVED_DISPLAY_COLOR_LIST);
	}

	public List<Integer> get_LevelFontSizeAdd() {
		return Arrays.asList(mUISettings.LevelFontSizeAdd);
	}


	public String get_BOOK_SETTINGS_FILE_PATH() {
		return mGeneralSettings.BOOK_SETTINGS_FILE_PATH;
	}


	public String get_BOOKMARKS_SAVE_FILE() {
		return mGeneralSettings.BOOKMARKS_SAVE_FILE;
	}

}
