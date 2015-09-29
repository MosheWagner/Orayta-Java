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
		IChapter chap = chapterContentsTree.getElementByID(id);
		
		//Try removing the last part of id; maybe it's too specific
		if (chap == null) chap = chapterContentsTree.getElementByID(despecifyId(id));
		
		return chap;
	}
	
	public TreeNode<IChapter> getChapterNodeByID(String id)
	{
		TreeNode<IChapter> node = chapterContentsTree.getElementNodeByID(id);
		
		//Try removing the last part of id; maybe it's too specific
		if (node == null) node = chapterContentsTree.getElementNodeByID(despecifyId(id));
		
		return node;
	}

	// Remove last element (after space) of the id, to make link less specific
	private String despecifyId(String id) 
	{
		int lastSpaceId = id.lastIndexOf(" ");
		
		if (lastSpaceId == -1) return id;
		
		return id.substring(0, lastSpaceId);
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
