package book.contents;

import java.util.ArrayList;
import java.util.List;

import tree.SearchableTree;
import tree.TreeNode;

public class BookContents 
{
	private SearchableTree<IChapter> chapterContentsTree = new SearchableTree<IChapter>();
	private List<ChapterAddress> flatIndex = new ArrayList<ChapterAddress>();
	private Boolean signMask[];
	
	public Boolean[] getSignMask() {
		return signMask;
	}

	public void setSignMask(Boolean[] signMask) {
		this.signMask = signMask;
	}

	public IChapter getChapterByID(String id)
	{
		return chapterContentsTree.getElementByID(id);
	}
	
	public TreeNode<IChapter> getChapterNodeByID(String id)
	{
		return chapterContentsTree.getElementNodeByID(id);
	}

	public TreeNode<IChapter> getChapterContentsTree() 
	{
		return chapterContentsTree.getElementsTree();
	}
	
	public void setChapterContentsTree(TreeNode<IChapter> chapterContentsTree) 
	{
		this.chapterContentsTree.setElementsTree(chapterContentsTree);
	}

	public List<ChapterAddress> getFlatIndex() {
		return flatIndex;
	}

	public void setFlatIndex(List<ChapterAddress> flatIndex) {
		this.flatIndex = flatIndex;
	}
}
