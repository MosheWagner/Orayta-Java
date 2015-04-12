package search;

import book.contents.ChapterID;

/*
 * This class holds the search results from each specific book.
 */

public interface ISearchResult 
{
	public ChapterID getChapterID();
	public String getPreview();
}
