package htmlRenderer;


import java.io.FileNotFoundException;
import java.io.IOException;

import fileManager.SFileReader;
import settings.SettingsManager;

import book.Book;

public class DCSSBuilder implements ICSSBuilder
{
	public DCSSBuilder(Book b) 
	{

	}

	private String userCss = "";
	
	//Font sizes each level's label (in the text itself) should get
	private static final int[] LevelFontSizeAdd = {2,12,18,20,24};
	
	private static final String USER_CSS_FILE_PATH = SettingsManager.generalSettings().BOOKS_ROOT_DIR + "../css/user.css";

	private String genericCSS(String fontFamily, int basesize)
	{
	    if(userCss == null) readUserCSS();

	    
		String css = "<style type=\"text/css\">\n"
	    + "   body { dir=\"RTL\"; text-align:justify; font-family:'" + fontFamily + "'; font-size:" + String.valueOf(basesize) + "px; }\n"

	    +        "   .L1 { font-family: '" + fontFamily + "'; font-size:" + String.valueOf(basesize + LevelFontSizeAdd[4]) + "px; font-weight:bold; color:indigo; }\n"
	    +        "   .L2 { font-family: '" + fontFamily + "'; font-size:" + String.valueOf(basesize + LevelFontSizeAdd[3]) + "px; font-weight:bold; color:indigo; }\n"
	    +        "   .L3 { font-family: '" + fontFamily + "'; font-size:" + String.valueOf(basesize + LevelFontSizeAdd[2]) + "px; font-weight:bold; color:indigo; }\n"
	    +        "   .L4 { font-family: '" + fontFamily + "'; font-size:" + String.valueOf(basesize + LevelFontSizeAdd[1]) + "px; font-weight:bold; color:indigo; }\n"
	    +        "   .L5 { font-family: '" + fontFamily + "'; font-size:" + String.valueOf(basesize + LevelFontSizeAdd[0]) + "px; font-weight:bold; color:indigo; }\n"
	    +        "   .L6 { font-family: '" + fontFamily + "'; font-size:" + String.valueOf(basesize + LevelFontSizeAdd[0]) + "px; font-weight:bold; color:indigo; }\n"
	    
	    +        "   .Aliyah { text-align: center; font-family:'" + fontFamily + "'; font-size:" + String.valueOf(basesize - 4) + "px; font-weight:bold; color:indigo; }\n"
	    +        "   .S0 { font-size:" + String.valueOf(basesize - 5) + "px;  font-weight:bold;}\n"
	    +        "   .copyright { font-size:" + String.valueOf((int)(basesize*0.6)) + "px; color:blue;}\n"

	    +        "   .VerySmall { font-size:" + String.valueOf((int)(basesize*0.7)) + "px;}\n"

	            // options from gmara nocha
	    +        "   .ref {font-size:" + String.valueOf((int)(basesize*0.8)) + "px;}\n"
	    +        "   .pasuk {font-family: \"SBL Hebrew\"; }\n"
	    +        "   .editor {color:#008A00;}\n"
	    +        "   .pirush {color:#2828AC;}\n"
	    +        "   .pirush, .editor{font-size:" + String.valueOf((int)(basesize*0.9)) + "px;}\n"
	    +        "   .small {font-size:" + String.valueOf((int)(basesize*0.7)) + "px;}\n"

	    +        "   div.Index A { font-family: '" + fontFamily + "'; color:indigo; }\n"

	    //+		 "   H6 {display: inline;} "
	    
	            //adding user defined styles here:
	    +        userCss + "\n"

	    +        "</style>\n";

	    return css;
	}
	
	
	private void readUserCSS() 
	{
		try 
		{
			userCss =  new SFileReader().readContents(USER_CSS_FILE_PATH);
		} 
		catch (FileNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String genCSS() 
	{
		String fontFamily = SettingsManager.uiSettings().FONT_NAME;
		int basesize = SettingsManager.uiSettings().BASE_FONT_SIZE;
		return genericCSS(fontFamily, basesize);
	}

}
