package tester.classTester;

import fileManager.IFileWriter;
import fileManager.SFileWriter;
import htmlRenderer.SHtmlRenderer;
import book.Book;
import book.contents.ChapterID;
import bookBuilder.BookHeaderBuilder;
import bookBuilder.obk.OBK_Builder;
import settings.SettingsManager;
import tester.ITest;
import tree.ITreeIterator;
import tree.TreeNode;

public class HtmlGenTest implements ITest
{

	private final String path = SettingsManager.generalSettings().BOOKS_ROOT_DIR + "001_mkra/01_torh/a01_Genesis.obk";
	private final String savepath = "/home/moshe/Desktop/a.html";
	
	public void Run() 
	{
		Book b = new BookHeaderBuilder().buildBook(path);
		b.setContents(new OBK_Builder().buildBookContents(b));
		
		ITreeIterator<TreeNode<ChapterID>> itr = b.getChapterIDList().iterator();
		itr.next(); 
		ChapterID chapid = itr.next().data;
		
		IFileWriter fw = new SFileWriter(); 
		fw.writeToFile(savepath, new SHtmlRenderer().renderChapter(b, chapid), true);
	}

}
