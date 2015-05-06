package bookmark.updatingBookmarks;

import java.util.Calendar;
import java.util.Date;

public class DateTools 
{
	/*
	 * Why the hell do I need to do this myself in 2015?! Seriously, Java, WTF?
	 * 
	 *  Anyway, hope this works. Thanks to SO, of course.
	 */  
	public static final long MILLIS_IN_DAY = 1000 * 60 * 60 * 24;  
	public static long daysBetween(Date startDate, Date endDate)
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
	
	public static Boolean sameDay(Date d1, Date d2)
	{
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(d1);
		
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(d2);

		return sameDay(cal1, cal2);
	}
	
	public static Boolean sameDay(Calendar c1, Calendar c2)
	{
		return (c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR)
				&& c1.get(Calendar.DAY_OF_YEAR) == c2.get(Calendar.DAY_OF_YEAR));
	}
}
