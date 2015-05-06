package tree;


public class SearchableTree<T extends IHasID>
{
	TreeNode<T> mBooks;
	IDSearcher<T> bookSearcher;
	
	public SearchableTree(TreeNode<T> books)
	{
		setElementsTree(books);
	}
	
	public T getElementByID(int uid)
	{
		return getElementByID(String.valueOf(uid));
	}
	
	public T getElementByID(String uid)
	{
		TreeNode<T> b = bookSearcher.findById(uid);
		if (b != null) return b.data;
		
		return null;
	}
	
	public void setElementsTree(TreeNode<T> books)
	{
		mBooks = books;
		bookSearcher = new IDSearcher<T>(mBooks);
	}
	
	public TreeNode<T> getElementsTree(){ return mBooks; }
}
