package bookTree;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import tree.TreeNode;

import book.Book;
import bookBuilder.BookHeaderBuilder;
import bookBuilder.FolderBookBuilder;
import bookBuilder.IBookBuilder;

/*
 * Builds the bookTree from the file system
 */

public class BookTreeBuilder
{
	TreeNode<Book> tree;

	IBookBuilder folderBuilder = new FolderBookBuilder();
	IBookBuilder emptyBookBuilder = new BookHeaderBuilder();
	
	public BookTree buildTree(String rootPath)
	{
		//Root is a folder called 'OraytaBooks'
		tree = new TreeNode<Book>(folderBuilder.buildBook(rootPath, "OraytaBooks"));
		
		List<File> files = Arrays.asList(new File(rootPath).listFiles());
		sortByLeadingNumber(files);
		addFilesToTreeNode((files), tree);

		return new BookTree(tree);
	}

	private final String OBK_SUFFIX = ".obk";
	private final String FOLDER_CONF_SUFFIX = ".folder";
	
	private void addFilesToTreeNode(List<File> files, TreeNode<Book> treeNode)
	{
		for (File f:files)
		{
			Book book;

			if (f.getName().endsWith(FOLDER_CONF_SUFFIX))
			{
				String folderPath = f.getAbsolutePath().replace(FOLDER_CONF_SUFFIX, "");
				
				book = folderBuilder.buildBook(folderPath);
				TreeNode<Book> branch = treeNode.addChild(book);
				
				List<File> children = Arrays.asList(new File(folderPath).listFiles());
				sortByLeadingNumber(children);
				addFilesToTreeNode(children, branch);
			}
			else if (f.getName().endsWith(OBK_SUFFIX))
			{
				book = emptyBookBuilder.buildBook(f.getAbsolutePath());
				treeNode.addChild(book);
			}
		}
	}
	
	private void sortByLeadingNumber(List<File> files)
	{
		Collections.sort(files, new Comparator<File>()
		{
		     public int compare(File f1, File f2)
		     {
		    	 String s1 = f1.getName().split("_")[0];
		    	 String s2 = f2.getName().split("_")[0];
		    	 
		    	 return s1.compareTo(s2);
		     }
		});
	}
}
