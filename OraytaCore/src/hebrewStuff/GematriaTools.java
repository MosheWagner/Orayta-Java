package hebrewStuff;

public class GematriaTools 
{
	public static String gematriaLetters(int n) { return gematriaLetters(n, false); }
	
	//Returns a hebrew string representing the given gematria value
	// You can tell it weather to quotation signs (Geresh or Gershaiim) or not
	public static String gematriaLetters (int num, Boolean showQuotationMarks)
	{
	    String hundredChars[] = {"ק","ר","ש","ת"};
	    String tenChars[] = {"י","כ","ל","מ","נ","ס","ע","פ","צ"};
	    String oneChars[] = {"א","ב","ג","ד","ה","ו","ז","ח","ט"};

	    String str = "";

	    //This should not happen, but to make sure we don't crash ot loop forever
	    if (num < 0) num = Math.abs(num);
	    
	    int tavs = (int)(num / 400);
	    for (int i=0; i<tavs; i++) str += "ת";
	    num = num % 400;


	    int hundreds = (int)(num / 100);
	    if (hundreds > 0 ) str += hundredChars[hundreds - 1];
	    num = num % 100;

	    //The two special cases
	    if (num == 16) str += "טז";
	    else if (num == 15) str += "טו";
	    else
	    {
	    	//Normal case
	        int tens = (int)(num / 10);
	        if (tens > 0) str += tenChars[tens-1];
	        num = num % 10;

	        if (num > 0) str += oneChars[num -1];
	    }

	    if (showQuotationMarks)
	    {
	        // '
	        if (str.length() == 1) str += "'";
	        // ""
	        else if (str.length() > 1)
	        {
	        	String lastChar = str.substring(str.length() - 1);
	            str = str.substring(0, str.length() - 1) + '\"';
	            str += lastChar;
	        }
	    }

	    return str;
	}
	
	//Returns the Gematria of the given string
	public static int gematriaValue (String str)
	{
	    int gematria = 0;
	    for(int i=0; i<str.length(); i++)
	        gematria += gematriaValue(str.charAt(i));

	    return gematria;
	}

	//Returns the gematria of the single given hebrew char
	public static int gematriaValue(char ch)
	{
	    String hchars = "אבגדהוזחטיךכלםמןנסעףפץצקרשת";

	    int vals[] = {0,1,2,3,4,5,6,7,8,9,10,20,20,30,40,40,50,50,60,70,80,80,90,90,100,200,300,400};
	    return vals[hchars.indexOf(ch)+1];
	}
	
	//Returns gmara page name of the given page number (From 4, which is Bet Amud Alef)
	public static String gmaraPageRepresentation(int num)
	{
	    char [] amud = {'.',':'};
	    return gematriaLetters(num / 2, false) + amud[num % 2];
	}
}
