package bookTree;

import book.Book;
import tree.PathSearcher;
import tree.SearchableTree;
import tree.TreeNode;

public class BookTree extends SearchableTree<Book>
{
	PathSearcher<Book> byPathSearcher;
	
	public BookTree(TreeNode<Book> books)
	{
		super(books);
		
		byPathSearcher = new PathSearcher<Book>(books);
	}

	public TreeNode<Book> getElementNodeByPath(String path)
	{
		if (byPathSearcher == null) return null;
		
		return byPathSearcher.findByPath(path);
	}
	
	public Book getElementByPath(String path)
	{
		TreeNode<Book> b = getElementNodeByPath(path);
		if (b != null) return b.data;
		
		return null;
	}
}
