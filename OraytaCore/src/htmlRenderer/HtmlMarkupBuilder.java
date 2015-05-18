package htmlRenderer;

import java.util.IllegalFormatException;

import book.contents.ChapterAddress;
import book.contents.IChapter;

/*
 * Helper class that helps create HTML elements when someone needs them.
 * Most of this consists of simple String returning static methods.
 */

public class HtmlMarkupBuilder 
{
	public final static String ARBITRAY_BEGINNING_MARK = "A";
	public static String createAnchor(String txt, String anchorID)
	{	
		String prefix = "<a id=\"" + ARBITRAY_BEGINNING_MARK + escapeToHex(anchorID) + "\"> ";
		String suffix = "</a>";
		
		return prefix + txt + suffix;
	}
	
	private static String genHtmlComment(String txt)
	{	
		return "<!--" + txt + "-->";
	}
	
	public static String genHtmlMarkerComment()
	{	
		return genHtmlComment("LevelMarker!");
	}
	
	public static String genMarkerAnchor(int levelCode, String markerText, String anchorId)
	{
		return createHeading(levelCode, createAnchor(markerText, anchorId));
	}
	
	
	public static String createHeading(int level, String txt)
	{
		String lvlStr = String.valueOf(level);
		
		String prefix = "";
		String suffix = "";
		
		prefix += "<BR>";
		
		//prefix += "<h" + lvlStr + "> ";
		//suffix += " </h" + lvlStr + ">\n";
		prefix += "<span class=\"L" + lvlStr + "\">";
		suffix += "</span>";
		
		return prefix + txt + suffix;
	}
	
	private static String createLink(String txt, String linkID) 
	{
		String prefix = "<a href=\"#" + ARBITRAY_BEGINNING_MARK + escapeToHex(linkID) + "\"> ";
		String suffix = "</a>";
		
		return prefix + txt + suffix;
	}
	
	public static String genChapTitle(IChapter c) 
	{
		int level = c.getChapterAddress().getChapterLevel() + 1;
		
		String linkID = c.getChapterAddress().getUID();
		String title = c.getChapterAddress().getTitle();
		return createHeading(level, createAnchor(title, linkID));
	}
	
	public static String genLinkToChapter(ChapterAddress address) 
	{
		int level = address.getChapterLevel() + 1;
		
		String anchorID = address.getUID();
		String title = address.getTitle();
		return createHeading(level, createLink(title, anchorID));
	}

	public static String genHeader(String title, ICSSBuilder cssBuilder)
	{
		String html;
		
		html = "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\"\n ";
		html += "\"http://www.w3.org/TR/html4/loose.dtd\">\n";
		html += "<html dir=\"RTL\">\n";
		html += "<head>\n\t";

		html += "<meta http-equiv=Content-Type content=\"text/html; charset=UTF-8\">";

		html += "\n<title>";
		html += title;
	    html += "</title>\n";
	    
	    //Add CSS settings to the html file:
	    html += cssBuilder.genCSS();
	    
	    html += "\n</head>";
	    
	    html += "\n<body>\n";
		
		return html;
	}
	
	public static String htmlEnd()
	{
		return "</body>\n</html>\n";
	}
	
	//	Because for some stupid reason some renderers can't handle internal HTML links with Hebrew chars, 
	//	this function converts any given Unicode String to a string representing each char's
	//	Unicode values in Hex, (separated by '\\u's), thus giving a valid digit-or-english-letter-only string.
	public static final int RADIX=32;
	public static final int CHAR_DIGITS=3;
	
	public static String escapeToHex(String str)
	{
	    String encoded = "";
	    
	    if (str == null) return "";
	    
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

	public static String genSpanClassPrefix(String classType, int index) 
	{
		return "<span class=" + classType + String.valueOf(index) + ">";
	}
	
	public static String genSpanSuffix() 
	{
		return "</span>";
	}
	
	public static String genItalicBold(String str) 
	{
		return "<i><b> " +  str + ": </b></i>";
	}
}
