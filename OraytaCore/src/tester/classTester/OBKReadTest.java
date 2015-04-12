package tester.classTester;

import book.bookTypes.ABook;
import book.bookTypes.Book_OBK;
import tester.ITest;

public class OBKReadTest implements ITest
{
	private final String path = "/home/moshe/Orayta/books/001_mkra/01_torh/a01_Genesis.obk";
	//private final String path = "/home/moshe/Orayta/books/030_tlmod_bbli/01_Bav_BRAHOT_L1.obk";
	
	public void Run() 
	{
		ABook b = new Book_OBK(path);
		b.buildContents();
		
		System.out.println(b.getChapterList().printTree());
	}
}
