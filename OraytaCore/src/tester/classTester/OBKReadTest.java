package tester.classTester;


import book.Book;
import bookBuilder.BookHeaderBuilder;
import bookBuilder.obk.OBK_Builder;
import settings.SettingsManager;
import tester.ITest;

public class OBKReadTest implements ITest
{
	private final String path = SettingsManager.generalSettings().BOOKS_ROOT_DIR + "001_mkra/01_torh/a01_Genesis.obk";
	//private final String path = "/home/moshe/Orayta/books/030_tlmod_bbli/01_Bav_BRAHOT_L1.obk";
	
	public void Run() 
	{
		Book b = new BookHeaderBuilder().buildBook(path);
		b.setContents(new OBK_Builder().buildBookContents(b));
		
		System.out.println(b.getChapterIDList().printTree());
		System.out.println(b.getContents().getFlatIndex());
	}
}
