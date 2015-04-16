package search;

import search.chapterMapping.ISortedNumberList;

public interface IPuretextSearchable 
{
	public void buildSearchIndex();
	
	public String getPureText();
	public ISortedNumberList getChapterMap();
}
