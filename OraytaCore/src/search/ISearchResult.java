package search;

import book.contents.ChapterAddress;

/*
 * This class holds the search results from each specific book.
 */

public interface ISearchResult 
{
	public ChapterAddress getChapterID();
	public String getPreview();
}
