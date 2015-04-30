package tree;

import java.util.TreeMap;

/*
 * This class allows us to find a TreeNode by it's data's UID.
 *  (That's why it works only for elements that extend IHasID)
 */

public class IDSearcher <T extends IHasID>
{
	private TreeMap<String, TreeNode<T>> mDataMap;
	
	public IDSearcher (TreeNode<T> tree)
	{
		mDataMap = new TreeMap<String, TreeNode<T>>();
		//TODO: Is the null test even needed?
		if (tree != null) buildSearchIndex(tree);
	}
	
	private void buildSearchIndex(TreeNode<T> tree) 
	{
		TreeIter<T> iter = (TreeIter<T>) tree.iterator();
		
		//mDataMap.put(iter.current().data.getUID(), iter.current());
		while(iter.hasNext())
		{
			TreeNode<T> node = iter.next();
			
			mDataMap.put(node.data.getUID(), node);
			//mDataMap.put(new Integer(node.data.getUID()), (TreeNode<T>) node.data);
		}
	}

	public TreeNode<T> findById(String id)
	{
		return mDataMap.get(id);
	}
}
