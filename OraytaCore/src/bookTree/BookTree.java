package bookTree;

import book.Book;
import book.contents.BookID;
import tree.IDSearcher;
import tree.TreeNode;

public class BookTree
{
	TreeNode<Book> mBooks;
	IDSearcher<Book> bookSearcher;
	
	public BookTree(TreeNode<Book> books)
	{
		setBookTree(books);
	}
			
	public Book getBook(BookID id)
	{
		TreeNode<Book> b = bookSearcher.findById(id.getUID());
		if (b != null) return b.data;
		
		return null;
	}
	
	public void setBookTree(TreeNode<Book> books)
	{
		mBooks = books;
		bookSearcher = new IDSearcher<Book>(mBooks);
	}
	
	public TreeNode<Book> getBookTree(){ return mBooks; }
}
