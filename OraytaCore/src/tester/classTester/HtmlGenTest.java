package tester.classTester;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import fileManager.SimplestFileReader;
import htmlRenderer.SHtmlRenderer;
import book.Book;

import book.contents.IChapter;
import bookBuilder.obk.OBK_Builder;
import bookTree.BookTree;
import bookTree.BookTreeBuilder;
import settings.SettingsManager;
import tester.ITest;
import tree.TreeNode;

import userSettings.bookSettings.BookSettingsManager;

public class HtmlGenTest implements ITest
{

	private final int ID;
	private final String chapStr;

	public HtmlGenTest() 
	{
		ID = 80005;
		chapStr = "סימן תרעה";
	}
	
	public HtmlGenTest(int id, String chapstr) 
	{
		ID = id;
		chapStr = chapstr;
	}
	
	public void Run() 
	{	
		BookSettingsManager bsmn = new BookSettingsManager();
		
		BookTreeBuilder tb = new BookTreeBuilder();
		BookTree bookTree = tb.buildTree(SettingsManager.getSettings().get_BOOKS_ROOT_DIR());
		
		Book b = bookTree.getElementByID(ID);
		b.setContents(new OBK_Builder(b).buildBookContents());
		
		TreeNode<IChapter> chapNode = b.getContents().getChapterNodeByID(chapStr);
		
		
		List<Book> weaved = new ArrayList<Book>();
		List<Integer> weavedBooksIds = bsmn.getData().get(b.getBookID()).getWeavedDisplayIDs();
		List<String> weavedBookstitles = bsmn.getData().get(b.getBookID()).getWeavedDisplayTitles();
		
		for (int i=0; i<weavedBooksIds.size(); i++)
		{
			Book w = bookTree.getElementByID(weavedBooksIds.get(i));
			
			if (w != null)
			{
				w.setContents(new OBK_Builder(w).buildBookContents());
				
				w.setDisplayNameWhenWeaved(weavedBookstitles.get(i));
				weaved.add(w);
			}
		}
		
		System.out.println(b.getContents().getChapterContentsTree());
		
		URL f = new SHtmlRenderer().renderChapter(b, chapNode.data.getChapterAddress(), weaved);
		
		System.out.println(f);
		
		try 
		{
			System.out.println((new SimplestFileReader()).readContents(f.getPath()));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
