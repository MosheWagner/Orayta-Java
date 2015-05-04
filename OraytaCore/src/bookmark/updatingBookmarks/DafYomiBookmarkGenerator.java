package bookmark.updatingBookmarks;

import hebrewStuff.GematriaTools;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import book.contents.BookID;
import book.contents.ChapterAddress;
import bookmark.Bookmark;

/*
 * Builds a bookmark that points to the current Daf Yomi
 */

public class DafYomiBookmarkGenerator implements IBookmarkBuilder 
{
	public Bookmark genBookmark() 
	{
		String dispName = "דף יומי";
		String dafPrefix = "דף ";
		
		int[] n = masechetAndDafFromDate(new Date());
		BookID bookID = new BookID(n[0]);
		
		int daf = n[1];
		String dafName = dafPrefix + GematriaTools.gematriaLetters(daf) + " - א";

		ChapterAddress addr = new ChapterAddress(bookID);
		addr.setAddress(dafName);

		return new Bookmark(addr, dispName);
	}


	
	private int[] masechetAndDafFromDate(Date d)
	{
		Date today = new Date();

		DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		
		int masechetID = -1;
		int masechetIndex = 0;
		int daf = -1;
		
	    ArrayList <Integer> masehtotUID = new ArrayList <Integer>();
	    masehtotUID.addAll(Arrays.asList(841,844,847,908,850,853,875,859,1106,888,862,892,1136,868,920,895,903,928,934,899,997,959,948,1003,1007,1014,1022,1027,1030,1040,1045,1132,1060,1035,1121,1117,1056));

	    ArrayList <Integer> dapim = new ArrayList <Integer>();
	    dapim.addAll(Arrays.asList(64,157,105,121,22,88,56,40,35,31,32,29,27,/*Yevamot*/122,112,91,66,49,90,82,/*Bava Kama*/119,119,176,113,24,49,76,14,/*Zevahim*/120,110,142,61,34,34,28,37,73 ));
		
	    int totalDapim = 0;
	    for (Integer n:dapim) totalDapim += n - 1;
	    
	    Date firstDaf = new Date();
		try 
		{
			// כ"ו בתמוז ה'תרפ"ג
			firstDaf = format.parse("10/07/1923");
		} 
		//This will not happen!
		catch (ParseException e) {} 
		
	    daf = (int) ((daysBetween(firstDaf, today) + 1) % totalDapim);

	    if ( daf < 0 ) return null;
	    else if (daf == 0) daf = totalDapim;

	    while (dapim.get(masechetIndex)-1 < daf)
	    {
	        daf -= dapim.get(masechetIndex++)-1;
	    }
	    daf += 1;
	    
	    masechetID = masehtotUID.get(masechetIndex);
	    
		int[] res = {masechetID, daf};
		return res;
	}
	
	/*
	 * Why the hell do I need to do this myself in 2015?! Seriously, Java, WTF?
	 * 
	 *  Anyway, hope this works. Thanks to SO, of course.
	 */  
	public static final long MILLIS_IN_DAY = 1000 * 60 * 60 * 24;  
	public long daysBetween(Date startDate, Date endDate)
	{
		Calendar startCal = Calendar.getInstance();
		Calendar endCal = Calendar.getInstance();
		
		startCal.setTime(startDate);
		endCal.setTime(endDate);
		
		long endInstant = endCal.getTimeInMillis();  
		long startInstant = startCal.getTimeInMillis();  
		
		int presumedDays = (int) ((endInstant - startInstant) / MILLIS_IN_DAY); 
		
		Calendar cursor = (Calendar) startCal.clone();  
		
		cursor.add(Calendar.DAY_OF_YEAR, presumedDays); 
		if (sameDay(cursor, endCal)) return presumedDays;

		//We missed. Probably because of DST or something similar.
		int step = cursor.getTimeInMillis() < endInstant ? 1 : -1;  
		for (int i=0; i<2 && !sameDay(cursor, endCal); i += step)
		{  
			cursor.add(Calendar.DAY_OF_MONTH, step);  
			presumedDays += step;  
		} 
		
		return presumedDays;  
	}
	
	private Boolean sameDay(Calendar c1, Calendar c2)
	{
		return (c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR)
				&& c1.get(Calendar.DAY_OF_YEAR) == c2.get(Calendar.DAY_OF_YEAR));
	}
}
