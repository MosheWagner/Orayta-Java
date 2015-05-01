package book.contents;

import java.util.ArrayList;
import java.util.List;

import tree.IDSearcher;
import tree.TreeNode;

public class BookContents 
{
	private TreeNode<ChapterAddress> chapterIDTree = new TreeNode<ChapterAddress>(null);
	private TreeNode<IChapter> chapterContentsTree = new TreeNode<IChapter>(null);
	private IDSearcher<IChapter> chapterContentsIndex = null;
	private List<ChapterAddress> flatIndex = new ArrayList<ChapterAddress>();
	
	public TreeNode<ChapterAddress> getChapterIDTree() {
		return chapterIDTree;
	}
	
	public void setChapterIDTree(TreeNode<ChapterAddress> chapterIDTree) {
		this.chapterIDTree = chapterIDTree;
	}
	
	public TreeNode<IChapter> getChapterByID(String id)
	{
		return chapterContentsIndex.findById(id);
	}

	public TreeNode<IChapter> getChapterContentsTree() {
		return chapterContentsTree;
	}
	public void setChapterContentsTree(TreeNode<IChapter> chapterContentsTree) {
		this.chapterContentsTree = chapterContentsTree;
		
		chapterContentsIndex = new IDSearcher<IChapter>(this.chapterContentsTree);
	}

	public List<ChapterAddress> getFlatIndex() {
		return flatIndex;
	}

	public void setFlatIndex(List<ChapterAddress> flatIndex) {
		this.flatIndex = flatIndex;
	}

}
