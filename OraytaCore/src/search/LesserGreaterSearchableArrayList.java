package search;

import java.util.ArrayList;

public class LesserGreaterSearchableArrayList implements ISortedNumberList
{
	ArrayList<Integer> mSoretedList = new ArrayList<Integer>();
	
	public LesserGreaterSearchableArrayList(ArrayList<Integer> sortedList)
	{
		mSoretedList = sortedList;
		//if (!sortedList.isSoreted) throw LIST_NOT_SORTET_EXCEPTION
	}
	
	//Finds the first index that holds a value larger or equal to the given number.
	// Returns -1 if the given number is bigger than the one in the last index, or if list is empty
	public int firstValueGreaterThan(Integer n) 
	{
		if (mSoretedList.size() == 0) return -1;
	
		if (n >= mSoretedList.get(mSoretedList.size() -1)) return -1;
		
		int low = 0;
		int high = mSoretedList.size();
		while (low != high) {
		    int mid = (low + high) / 2; // Or a fancy way to avoid overflow
		    if (mSoretedList.get(mid) <= n) {
		        /* This index, and everything below it, must not be the first element
		         * greater than what we're looking for because this element is no greater
		         * than the element.
		         */
		        low = mid + 1;
		    }
		    else {
		        /* This element is at least as large as the element, so anything after it can't
		         * be the first element that's at least as large.
		         */
		        high = mid;
		    }
		}
		
		return high;
	}
	
	//Finds the first index that holds a value smaller or equal to the given number.
	// Returns -1 if the given number is smaller than the one in the first index, or if list is empty
	public int lastValueEqualOrLesserThan(Integer n) 
	{
		if (mSoretedList.size() == 0) return -1;
		
		if (n < mSoretedList.get(0)) return -1;
		
		int ind = firstValueGreaterThan(n);
		
		//This means the number is bigger than the one in the last index of our array, so return the last index
		if (ind == -1) return mSoretedList.size() -1;
		
		//Because the index of the first lesserOrEqual to the given number,
		// is one before the first one that's greater than it
		return ind - 1;
	}
}
