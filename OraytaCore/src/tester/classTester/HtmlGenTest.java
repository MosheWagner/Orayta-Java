package tester.classTester;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import fileManager.IFileWriter;
import fileManager.SFileWriter;
import htmlRenderer.SHtmlRenderer;
import book.Book;
import book.contents.IChapter;
import bookBuilder.BookHeaderBuilder;
import bookBuilder.DBookBuildersFactory;
import bookBuilder.obk.OBK_Builder;
import settings.SettingsManager;
import tester.ITest;

public class HtmlGenTest implements ITest
{

	private final String path = SettingsManager.generalSettings().BOOKS_ROOT_DIR + "001_mkra/01_torh/a01_Genesis.obk";
	//private final String path = SettingsManager.generalSettings().BOOKS_ROOT_DIR + "030_tlmod_bbli/01_Bav_BRAHOT_L1.obk";
	private final String savepath = "/home/moshe/Desktop/a.html";
	
	public void Run() 
	{	
		Book b = new BookHeaderBuilder().buildBook(path);
		b.setContents(new OBK_Builder().buildBookContents(b));
		
		//TreeNode<IChapter> chapnode = b.getContents().getChapterByID("בראשית פרק-יב");
		IChapter chap = b.getContents().getChapterByID("בראשית פרק-יח");
		//TreeNode<IChapter> chapnode = b.getContents().getChapterByID("בראשית פרק-יט");
		//TreeNode<IChapter> chapnode = b.getContents().getChapterByID("דף יג - א");
		//IChapter chap = b.getContents().getChapterByID("דף לה - א");
		
		Collection<Book> weaved = new ArrayList<Book>();
		List<String []> weavedcodes = b.getWaevedSources();
		for (String[] src:weavedcodes)
		{
			String srcPath = SettingsManager.generalSettings().BOOKS_ROOT_DIR + src[0];
			String srcTitle = src[1];
			Book w = new DBookBuildersFactory().getBookBuilder(srcPath).buildBook(srcPath);
			w.setDisplayName(srcTitle);
			if (w != null)
			{
				w.setContents(new OBK_Builder().buildBookContents(w));
				weaved.add(w);
			}
		}
		
		
		String html = new SHtmlRenderer().renderChapter(b, chap.getChapterAddress(), weaved);
		
		System.out.println(html);
		
		IFileWriter fw = new SFileWriter(); 
		fw.writeToFile(savepath, html, true);
	}

}
