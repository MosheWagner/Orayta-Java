package book.contents;

import tree.IHasID;

public interface IChapter extends IHasID // extends Comparable<IChapter>
{
	//Returns the 'address' of this chapter 
	public ChapterAddress getChapterAddress();
	
	//Returns true if the chapter is a lowest level chapter (and therefore has it's own text) 
	public boolean isAtom();
	
	//Returns the text in the chapter. If this is not an atom (like a high level chapter), this returns an empty string.
	public String text();
	public String plainText();

	public void setChapterText(String string);
}
