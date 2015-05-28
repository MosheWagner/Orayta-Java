package htmlRenderer;

import java.io.FileNotFoundException;
import java.io.IOException;

import fileManager.SFileReader;
import settings.SettingsManager;

import book.Book;

public class DCSSBuilder implements ICSSBuilder
{
	//TODO: Is the book needed?
	public DCSSBuilder(Book b) 
	{

	}

	private String userCss = "";
	


	private String genericCSS(String fontFamily, int basesize)
	{
	    if(userCss == null) readUserCSS();

	    
		String css = "<style type=\"text/css\">\n"
	    + "   body { dir=\"RTL\"; text-align:justify; font-family:'" + fontFamily + "'; font-size:" + String.valueOf(basesize) + "px; }\n"

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

	    +        "   div.Index A { font-family: '" + fontFamily + "'; color:indigo; }\n";

	    //+		 "   H6 {display: inline;} "
	    
	    //Level classes
		int c = 0;
		for (String color:SettingsManager.getSettings().get_WEAVED_DISPLAY_COLOR_LIST())
		{
			css += "   .W" + String.valueOf(c++) + " {color:" + color + "; }\n";
		}
		
	    //Level classes (start from 1)
		c = 1;
		for (int size:SettingsManager.getSettings().get_LevelFontSizeAdd())
		{
			css += "   .L" + String.valueOf(c++) 
					+ "{ font-family: '" + fontFamily 
					+ "'; font-size:" + String.valueOf(basesize + size) 
					+ "px; font-weight:bold; color:indigo; }\n";
		}
	    
	    
	            //adding user defined styles here:
	    css +=        userCss + "\n"

	    +        "</style>\n";

	    return css;
	}
	
	
	private void readUserCSS() 
	{
		try 
		{
			userCss =  new SFileReader().readContents(SettingsManager.getSettings().get_USER_CSS_FILE_PATH());
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
		String fontFamily = SettingsManager.getSettings().get_FONT_NAME();
		int basesize = SettingsManager.getSettings().get_BASE_FONT_SIZE();
		return genericCSS(fontFamily, basesize);
	}

}
