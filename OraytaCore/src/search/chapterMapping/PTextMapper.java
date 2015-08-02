package search.chapterMapping;

import book.Book;
import book.contents.ChapterAddress;
import bookBuilder.obk.OBK_ParseHelper;

public class PTextMapper extends ALevelMapper
{
	int mBookId;
	ISortedNumberList sortedIndexes;
	
	public PTextMapper(Book book, String LevelMapText) 
	{
		this(book.getBookID(), LevelMapText);
	}
	
	public PTextMapper(int bookID, String LevelMapText) 
	{
		mBookId = bookID;
		buildIndex(LevelMapText);
	}

	private void buildIndex(String levelMapText) 
	{
		for (String line:levelMapText.split("\\r?\\n"))
		{
			String[] lineParts = line.split("->");
			if (lineParts.length == 2)
			{
				indexList.add(Integer.parseInt(lineParts[0]));
				chapterList.add(decodeChapter(lineParts[1]));
			}
		}
		
		sortedIndexes = new BoundarySearchableArrayList(indexList);
	}

	private ChapterAddress decodeChapter(String chapStr) 
	{
		return OBK_ParseHelper.addressFromLevelStrArr(mBookId, chapStr.split("[*]"));
	}


	@Override
	public ChapterAddress mapToChaper(int searchDBindex) 
	{
		int i = sortedIndexes.lastValueEqualOrLesserThan(searchDBindex);

		if (i == -1) return null;
		return chapterList.get(i);
	}

	@Override
	public int mapToSearchDBIndex(ChapterAddress address) 
	{
		int ind = 0;
		for (int i=0; i<chapterList.size(); i++)
		{
			if (chapterList.get(i).equals(address)) ind = i;
		}

		return indexList.get(ind);
	}

}
