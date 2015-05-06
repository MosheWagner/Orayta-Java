package bookTree;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import tree.SearchableTree;
import tree.TreeNode;

import book.Book;
import bookBuilder.BookHeaderBuilder;
import bookBuilder.DBookBuildersFactory;
import bookBuilder.FolderBookBuilder;
import bookBuilder.IBookBuilder;
import bookBuilder.IBookBuildersFactory;

/*
 * Builds the bookTree from the file system
 */

public class BookTreeBuilder
{
	TreeNode<Book> tree;

	IBookBuilder folderBuilder = new FolderBookBuilder();
	IBookBuilder emptyBookBuilder = new BookHeaderBuilder();
	
	public SearchableTree<Book> buildTree(String rootPath)
	{
		//Root is a folder called 'OraytaBooks'
		tree = new TreeNode<Book>(folderBuilder.buildBook(rootPath, "OraytaBooks"));
		
		List<File> files = Arrays.asList(new File(rootPath).listFiles());
		sortByLeadingNumber(files);
		addFilesToTreeNode((files), tree);

		return new SearchableTree<Book>(tree);
	}


	
	private void addFilesToTreeNode(List<File> files, TreeNode<Book> treeNode)
	{
		for (File f:files)
		{
			IBookBuilder builder = new DBookBuildersFactory().getBookBuilder(f.getPath());
			
			if (builder != null)
			{
				Book book = builder.buildBook(f.getAbsolutePath());
				TreeNode<Book> branch = treeNode.addChild(book);
				
				//It's a folder, so add the files within it, too
				if (builder.isContainer())
				{
					String folderPath = f.getAbsolutePath().replace(IBookBuildersFactory.FOLDER_CONF_SUFFIX, "");
					List<File> children = Arrays.asList(new File(folderPath).listFiles());
					sortByLeadingNumber(children);
					addFilesToTreeNode(children, branch);
				}
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
