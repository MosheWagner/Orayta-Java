package html;

import java.util.IllegalFormatException;

/*
 * Helper class that helps create HTML elements when someone needs them.
 * Most of this is just simple String returning static methods.
 */
public class HtmlBuilder 
{
	public static String createAnchor(String txt)
	{	
		return createAnchor(txt, escapeToHex(txt));
	}
	
	public static String createAnchor(String txt, String anchorHexID)
	{	
		String prefix = "<a id=\"" + anchorHexID + "\"> ";
		String suffix = " </a>";
		
		return prefix + txt + suffix;
	}
	
	//	Because for some stupid reason some renderers can't handle internal HTML links with Hebrew chars, 
	//	this function converts any given Unicode String to a string representing each char's
	//	Unicode values in Hex, (separated by '\\u's), thus giving a valid digit-or-english-letter-only string.
	public static final int RADIX=32;
	public static final int CHAR_DIGITS=3;
	
	public static String escapeToHex(String str)
	{
	    String encoded = "";
	    
	    //Make sure we have no extra spaces
	    str = str.trim();
	    
	    for (char c:str.toCharArray()) 
    	{
	    	String numStr = String.format(Integer.toString((int) c, RADIX)) ;
	    	String paddedStr = String.format("%1$" + CHAR_DIGITS + "s", numStr).replace(' ', '0');
	    	
	    	encoded += paddedStr;
    	}

	    return encoded;
	}
	
	public static String unEscapeFromHex(String str) throws IllegalFormatException
	{
	    String origStr = "";
	    
	    if (str.length() % CHAR_DIGITS != 0) throw new IllegalArgumentException(str + " is not a legal hex string!");
	    
	    for (int i=0; i<str.length() - CHAR_DIGITS + 1; i+=CHAR_DIGITS) 
	    {
	    	String uchar = str.substring(i, i+CHAR_DIGITS);
	    	origStr += (char) Integer.parseInt(uchar, RADIX);
	    }

	    return origStr; 
	}
	
	public static String createHeading(int level, String txt)
	{
		String lvlStr = String.valueOf(level);
		String prefix = "<h" + lvlStr + "> ";
		String suffix = " </h" + lvlStr + ">";
		
		return prefix + txt + suffix;
	}
}
