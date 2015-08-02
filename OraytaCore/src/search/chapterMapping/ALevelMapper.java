package search.chapterMapping;

import java.util.ArrayList;

import book.contents.ChapterAddress;

/*
 * This class provides a two way mapping between chapters and their index in the search DB
 */

public abstract class ALevelMapper 
{
	protected ArrayList<ChapterAddress> chapterList = new ArrayList<ChapterAddress>();
	protected ArrayList<Integer> indexList = new ArrayList<Integer>();
	
	public abstract ChapterAddress mapToChaper(int searchDBindex);
	
	public abstract int mapToSearchDBIndex(ChapterAddress address);
}
