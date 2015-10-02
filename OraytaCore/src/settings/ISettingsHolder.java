package settings;

import java.util.List;

public interface ISettingsHolder 
{
	//General settings:
	
	public String get_BOOKS_ROOT_DIR();
	public String get_BOOKS_SAVE_PATH();
	
	public String get_SERVER_BOOK_ROOT_URL();
	public String get_BOOK_LIST_DOWNLOAD_URL();
	
	public String get_HTML_RENDERED_FILES_PATH();
	
	public String get_DAILY_LIMUD_FILE_PATH();
	
	public String get_USER_CSS_FILE_PATH();
	
	public String get_BOOKMARKS_SAVE_FILE();
	public String get_LV_BOOKMARKS_SAVE_FILE();
	
	//UI Settings:
	
	public String get_FONT_NAME();
	public int get_BASE_FONT_SIZE();
	
	public List<String> get_WEAVED_DISPLAY_COLOR_LIST();
	
	//Font sizes each level's label (in the text itself) should get
	public List<Integer> get_LevelFontSizeAdd();

	public String get_BOOK_SETTINGS_FILE_PATH();

	
}
