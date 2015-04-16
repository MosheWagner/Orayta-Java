package bookBuilder.obk;


import htmlRenderer.HtmlMarkupBuilder;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;



public class OBK_ParseHelper 
{
	//Returns the Html code for the given external link
	public static String decodeExternalLink (String linkcode)
	{
	    @SuppressWarnings("unused")
		Boolean ToraOr;
		@SuppressWarnings("unused")
	    Boolean ShouldBePrintedOnNewLine;
	    
	    //UniqueId of target link
	    int  BookUniqueId = 0;
	    
	    //Label within the target book
	    String BookInternalLabel = "";
	    
	    //Link's display name
	    String DisplayedText = "";
	    
	    //Link's display style
	    int  DisplayStyle = 0;  // BITS:  0-bold, 1-underline, 2-italic, 3-small, 4-big, 5-red, 6-green, 7-blue

	    //Validate line
	    if (linkcode.startsWith("<!--ex") == false)
	        return "";

	    //Check type
	    char codeChar =  linkcode.charAt(6);
	    if (codeChar == 'a')
	    {
	        ToraOr = false;
	        ShouldBePrintedOnNewLine = false;
	    }
	    else if (codeChar == 'b')
	    {
	        ToraOr = false;
	        ShouldBePrintedOnNewLine = true;
	    }
	    else if (codeChar == 'c')
	    {
	        ToraOr = true;
	        ShouldBePrintedOnNewLine = false;
	    }
	    else
	        return "";

	    //Find location of "-->"
	    int ind = linkcode.indexOf("-->");

	    //Get displayed text
	    DisplayedText = linkcode.substring(ind+3);

	    //Get data : skip the first 7 chars - cut of the lenth of the displayed text and the "-->"
	    String Data = linkcode.substring(7, ind-4); 

	    //Decrypt link, and convert the data to unicode
	    Data = Decrypt(Data);
	    
	    if (Data == "")
	        return "";

	    //Split to parts
	    List<String> splitStr = Arrays.asList(Data.split(Pattern.quote("|")));

	    //Get display style
	    try
	    {
	    	DisplayStyle = Integer.valueOf(splitStr.get(0));

		    if(!splitStr.get(1).equals(""))
		    {
		        //If part starts with "bm:"
		        if(splitStr.get(1).startsWith("bm:"))
		        {
		            //Find #
		        	ind = splitStr.get(1).indexOf("#");
		        	
		        	BookUniqueId = Integer.valueOf(splitStr.get(1).substring(3, ind-1));
		            
		            //Get BookInternalLabel
		            BookInternalLabel = splitStr.get(1).substring(ind+1).trim();
		            
		        	System.out.println(BookInternalLabel);
		        }
		    }
	    }
	    catch (NumberFormatException e) {return "";}

	    String Html="";

	    // DisplayStyle BITS:  0-bold, 1-underline, 2-italic, 3-small, 4-big, 5-red, 6-green, 7-blue
	    int DS = DisplayStyle;

	    //I'm ignoring the colors at the moment
	    if ( (DS>>0 & 0x1) != 0) Html += "<B>";
	    //if ( DS>>1 & 0x1 ) Html += "<U>";
	    if ( (DS>>2 & 0x1) != 0) Html += "<I>";
	    //if ( DS>>3 & 0x1 ) Html += "<small>";
	    if ( (DS>>4 & 0x1) != 0) Html += "<big>";

	    //if (ShouldBePrintedOnNewLine)
	    //    Html += "<BR>\n";

	    // Eliminate spaces
	    String linkto = BookInternalLabel;

	    //Escape the hebrew chars
	    linkto = HtmlMarkupBuilder.escapeToHex(linkto);

	    if (BookUniqueId != 0)
	    {
	        Html += "<a href=\"!" + BookUniqueId;
	        Html += ":" +  linkto + "\">";
	        Html += DisplayedText + "</a>";
	    }
	    else
	    {
	        Html += DisplayedText;
	    }

	    //I'm ignoring the coloers at the moment
	    //revert order to respect html tags nested
	    if ( (DS>>4 & 0x1) != 0) Html += "</big>";
	    //if ( DS>>3 & 0x1 ) Html += "</small>";
	    if ( (DS>>2 & 0x1) != 0) Html += "</I>";
	    //if ( DS>>1 & 0x1 ) Html += "</U>";
	    if ( (DS>>0 & 0x1) != 0) Html += "</B>";

	    return Html;
	}
	
	// These two functions encode and decode a hebrew string to only English chars. 
	//  This is done by splitting each encoded char into 2 and string it as 2 ascii chars, and vice versa.
	//
	// Implementation was done with help of the developer of the original program
	
	public static String Decrypt (String text) 
	{
		byte[] asciiVals = text.getBytes(Charset.forName("US-ASCII"));
		byte[] isoStr = new byte[asciiVals.length];

		int j=0;
		for (int i=0; i<asciiVals.length; i++)
		{
			byte b = asciiVals[i];

			if (i % 2 == 0 && i < asciiVals.length -1)
			{ 	
				byte u1 = (byte) (b - 'A');
		    	byte u2 = (byte) ((asciiVals[i+1] - 'A') << 4);
		    	byte u = (byte) (u1 | u2);
		    	
		    	isoStr[j++] = u;
			}
		}
		
		return toUnicode(isoStr, "ISO-8859-8");
	}
	
	public static String Encrypt (String text) 
	{
		byte[] asciiVals = text.getBytes(Charset.forName("ISO-8859-8"));
		byte[] isoStr = new byte[asciiVals.length * 2];

		int j=0;
		for (byte b:asciiVals)
		{
			byte d = (byte) ('A' + (b & 15));
			byte d2 = (byte)('A' + ((b >> 4) & 15));
			
			isoStr[j++] = d;
			isoStr[j++] = d2;
		}
		
		return toUnicode(isoStr, "US-ASCII");
	}

	private static String toUnicode(byte[] isoStr, String charset) 
	{
		return new String(isoStr, Charset.forName(charset));
	}
	
	
}
