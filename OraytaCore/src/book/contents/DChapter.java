package book.contents;

public class DChapter implements IChapter {

	ChapterAddress mAddress;
	String mChapterText = "";
	
	public DChapter() {}
	
	public DChapter(ChapterAddress address, String text)
	{
		mAddress = address;
		mChapterText = text;
	}
	
	public void setAddress(ChapterAddress address) { mAddress=address; }
	public void setChapterText(String chapterText) { mChapterText=chapterText; }


	public ChapterAddress getChapterAddress() {
		return mAddress;
	}

	public boolean isAtom() {
		return (!text().equals(""));
	}

	public String text() {
		return mChapterText;
	}

	public String plainText() 
	{
		// TODO Auto-generated method stub
		return null;
	}

	public String getUID() {
		return mAddress.getUID();
	}

	@Override
	public String toString() {
		return "[mAddress=" + mAddress + ", mChapterText=" + mChapterText + "]";
	}
	
	
}
