package search.chapterMapping;

/*
 * This interface allows us to 'binary search' for boundary results in a sorted integer list
 */

public interface ISortedNumberList 
{
	public int lastValueEqualOrLesserThan(Integer n);
	public int firstValueGreaterThan(Integer n);
}
