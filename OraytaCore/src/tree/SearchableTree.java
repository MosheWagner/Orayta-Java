package tree;


public class SearchableTree<T extends IHasID>
{
	TreeNode<T> mBooks = null;
	IDSearcher<T> bookSearcher = null;
	
	public SearchableTree() {}
	
	public SearchableTree(TreeNode<T> books)
	{
		setElementsTree(books);
	}
	
	public TreeNode<T> getElementNodeByID(int uid)
	{
		return getElementNodeByID(String.valueOf(uid));
	}
	
	public TreeNode<T> getElementNodeByID(String uid)
	{
		if (bookSearcher == null) return null;
		
		return bookSearcher.findById(uid);
	}
	
	public T getElementByID(int uid)
	{
		return getElementByID(String.valueOf(uid));
	}
	
	public T getElementByID(String uid)
	{
		TreeNode<T> b = getElementNodeByID(uid);
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
