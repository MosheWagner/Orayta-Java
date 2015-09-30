package adapters;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;

import tree.TreeNode;

public class TreeAdapter <T>
{
	public TreeModel genTreeModel(TreeNode<T> tree)
	{
		javax.swing.tree.DefaultMutableTreeNode baseNode = new DefaultMutableTreeNode(tree.data);

		addChildren(baseNode, tree);
		
		return new DefaultTreeModel(baseNode);
	}

	private void addChildren(DefaultMutableTreeNode baseNode, TreeNode<T> tree) 
	{
		for (TreeNode<T> child:tree.children)
		{
			javax.swing.tree.DefaultMutableTreeNode childBaseNode = new DefaultMutableTreeNode(child.data);
			addChildren(childBaseNode, child);
			
			baseNode.add(childBaseNode);
		}
	}
}
