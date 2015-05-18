package tester.classTester;

import java.util.ArrayList;
import java.util.List;

import fileManager.IFileWriter;
import fileManager.SFileWriter;
import htmlRenderer.SHtmlRenderer;
import book.Book;

import book.contents.IChapter;
import bookBuilder.BookHeaderBuilder;
import bookBuilder.obk.OBK_Builder;
import bookTree.BookTree;
import bookTree.BookTreeBuilder;
import settings.SettingsManager;
import tester.ITest;

import userSettings.bookSettings.BookSettingsManager;

public class HtmlGenTest implements ITest
{

	private final String path = SettingsManager.getSettings().get_BOOKS_ROOT_DIR() + "001_mkra/01_torh/a01_Genesis.obk";
	//private final String path = SettingsManager.getSettings().get_BOOKS_ROOT_DIR() + "030_tlmod_bbli/01_Bav_BRAHOT_L1.obk";
	private final String savepath = "/home/moshe/Desktop/a.html";
	
	public void Run() 
	{	
		BookSettingsManager bsmn = new BookSettingsManager();
		
		BookTreeBuilder tb = new BookTreeBuilder();
		BookTree bookTree = tb.buildTree(SettingsManager.getSettings().get_BOOKS_ROOT_DIR());
		
		Book b = new BookHeaderBuilder().buildBook(path);
		b.setContents(new OBK_Builder().buildBookContents(b));
		
		//TreeNode<IChapter> chapnode = b.getContents().getChapterByID("בראשית פרק-יב");
		IChapter chap = b.getContents().getChapterByID("בראשית פרק-יח");
		//TreeNode<IChapter> chapnode = b.getContents().getChapterByID("בראשית פרק-יט");
		//IChapter chap = b.getContents().getChapterByID("דף יג - א");
		//IChapter chap = b.getContents().getChapterByID("דף לה - א");
		

//		List<Integer> weavedIds = new ArrayList<Integer>();
//		List<String> weavedTitles = new ArrayList<String>();
//		
//		List<String []> weavedcodes = b.getPossibleWaevedSources();
//		for (String[] src:weavedcodes)
//		{
//			Book w = bookTree.getElementByPath(SettingsManager.getSettings().get_BOOKS_ROOT_DIR() + src[0]);
//			
//			if (w != null)
//			{
//				weavedIds.add(w.getBookID());
//				weavedTitles.add(src[1]);
//			}
//		}
//		
//		SingleBookSettings bs = new SingleBookSettings();
//		bs.setWeavedDisplayIDs(weavedIds);
//		bs.setWeavedDisplayTitles(weavedTitles);
//		
//		bsmn.getSettingsMapper().put(b.getBookID(), bs);
//		bsmn.saveToFile();
		
		
		List<Book> weaved = new ArrayList<Book>();
		List<Integer> weavedBooksIds = bsmn.getSettingsMapper().get(b.getBookID()).getWeavedDisplayIDs();
		List<String> weavedBookstitles = bsmn.getSettingsMapper().get(b.getBookID()).getWeavedDisplayTitles();
		
		for (int i=0; i<weavedBooksIds.size(); i++)
		{
			Book w = bookTree.getElementByID(weavedBooksIds.get(i));
			
			if (w != null)
			{
				w.setContents(new OBK_Builder().buildBookContents(w));
				
				w.setDisplayNameWhenWeaved(weavedBookstitles.get(i));
				weaved.add(w);
			}
		}
		
		String html = new SHtmlRenderer().renderChapter(b, chap.getChapterAddress(), weaved);
		//String html = new SHtmlRenderer().renderFullBook(b, weaved);
		
		//System.out.println(html);
		
		IFileWriter fw = new SFileWriter(); 
		fw.writeToFile(savepath, html, true);
	}

}
