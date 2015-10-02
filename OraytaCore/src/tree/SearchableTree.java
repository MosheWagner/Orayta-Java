package tree;


public class SearchableTree<T extends IHasID>
{
	TreeNode<T> mElems = null;
	IDSearcher<T> elemSearcher = null;
	
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
		if (elemSearcher == null) return null;
		
		return elemSearcher.findById(uid);
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
		mElems = books;
		elemSearcher = new IDSearcher<T>(mElems);
	}
	
	public TreeNode<T> getElementsTree(){ return mElems; }
}
