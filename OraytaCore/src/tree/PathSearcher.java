package tree;

import java.util.TreeMap;

public class PathSearcher <T extends IHasPath>
{
	private TreeMap<String, TreeNode<T>> mDataMap = new TreeMap<String, TreeNode<T>>();
	
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
