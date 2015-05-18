package tree;

import java.util.HashMap;
import java.util.Map;

public class PathSearcher <T extends IHasPath>
{
	private Map<String, TreeNode<T>> mDataMap = new HashMap<String, TreeNode<T>>();
	
	public PathSearcher (TreeNode<T> tree)
	{
		//TODO: Is the null test even needed?
		if (tree != null) buildPathSearchIndex(tree);
	}
	
	private void buildPathSearchIndex(TreeNode<T> tree) 
	{
		TreeIter<T> iter = (TreeIter<T>) tree.iterator();
		
		while(iter.hasNext())
		{
			TreeNode<T> node = iter.next();
			
			mDataMap.put(node.data.getPath(), node);
		}
	}

	public TreeNode<T> findByPath(String id)
	{
		if (mDataMap.containsKey(id)) return mDataMap.get(id);
		
		return null;
	}
}
