package tree;

import java.util.HashMap;
import java.util.Map;

/*
 * This class allows us to find a TreeNode by it's data's UID.
 *  (That's why it works only for elements that extend IHasID)
 */

public class IDSearcher <T extends IHasID>
{
	private Map<String, TreeNode<T>> mDataMap = new HashMap<String, TreeNode<T>>();
	
	public IDSearcher (TreeNode<T> tree)
	{
		//TODO: Is the null test even needed?
		if (tree != null) buildSearchIndex(tree);
	}
	
	private void buildSearchIndex(TreeNode<T> tree) 
	{
		TreeIter<T> iter = (TreeIter<T>) tree.iterator();
		
		while(iter.hasNext())
		{
			TreeNode<T> node = iter.next();
			
			mDataMap.put(node.data.getUID(), node);
		}
	}

	public TreeNode<T> findById(String id)
	{
		if (mDataMap.containsKey(id)) return mDataMap.get(id);
		
		return null;
	}
}
