package tree;

import java.util.Collection;

/*
 * The way any good tree should act.
 */

public interface ITree<T> {
	public boolean isRoot();
	public boolean isLeaf();
	public int getLevel();
	
	public TreeNode<T> addChild(T child);
	public Collection<TreeNode<T>> getChildren();
	
	public void removeElement(TreeNode<T> node);

	public Collection<TreeNode<T>> findTreeNodes(T data);
}
