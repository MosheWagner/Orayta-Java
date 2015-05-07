package settings;


import javax.xml.bind.annotation.*;

@XmlRootElement
public class UISettings 
{
	public String FONT_NAME = "Droid Sans Hebrew Orayta";
	public int BASE_FONT_SIZE = 18;
	
	public String[] WEAVED_DISPLAY_COLOR_LIST = {"#009000", "#0000FF", "#A52A2A", "#4B0082",
												 "#009000", "#0000FF", "#A52A2A", "#4B0082",
												 "#009000", "#0000FF", "#A52A2A", "#4B0082"};
	
	//Font sizes each level's label (in the text itself) should get
	public int[] LevelFontSizeAdd = {24,20,18,12,2,0,0};

}
