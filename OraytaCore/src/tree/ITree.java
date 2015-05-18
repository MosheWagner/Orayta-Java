package tree;

import java.util.List;

/*
 * The way any good tree should act.
 */

public interface ITree<T> {
	public boolean isRoot();
	public boolean isLeaf();
	public int getLevel();
	
	public TreeNode<T> addChild(T child);
	public List<TreeNode<T>> getChildren();
	
	public void removeElement(TreeNode<T> node);

	public List<TreeNode<T>> findTreeNodes(T data);
	
	//Returns a list of all siblings (including siblings' siblings, recursively)
	public List<T> deepSiblingsList();
}
