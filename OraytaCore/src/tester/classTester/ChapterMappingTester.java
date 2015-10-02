package tester.classTester;

import java.io.FileNotFoundException;
import java.io.IOException;

import book.Book;
import book.contents.ChapterAddress;
import book.contents.IChapter;
import bookBuilder.obk.OBK_Builder;
import bookTree.BookTree;
import bookTree.BookTreeBuilder;

import fileManager.IZipReader;
import fileManager.ZipReader;
import search.chapterMapping.PTextMapper;
import settings.SettingsManager;
import tester.ITest;
import tree.ITreeIterator;
import tree.TreeNode;

public class ChapterMappingTester implements ITest
{

	String SDB = "SearchDB";
	String LMP = "LevelMap";
	
	
	public void Run() 
	{
		BookTree bt = new BookTreeBuilder().buildTree(SettingsManager.getSettings().get_BOOKS_ROOT_DIR());
		
		Book b = bt.getElementByID(760);
		b.setContents(new OBK_Builder(b).buildBookContents());
		
		IZipReader reader = new ZipReader();
		
		String db = "";
		String map = "";
		try {
			db = reader.readContents(b.getPath(), SDB);
			map = reader.readContents(b.getPath(), LMP);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		PTextMapper mapper = new PTextMapper(760, map);
		
		ChapterAddress c = mapper.mapToChaper(40);
		System.out.println(c.getUID());
		System.out.println("SDB:" + db);
		
		ITreeIterator<TreeNode<IChapter>> a = b.getContents().getChapterContentsTree().iterator();
		a.next(); a.next(); a.next();
		
		System.out.println(a.next().data.getChapterAddress().getFullAddress());
		
		if (b.getContents() != null)
		{
			TreeNode<IChapter> chapNode = b.getContents().getChapterNodeByID(c.getUID());
			System.out.println(chapNode);
		}
	}

}
