package book.contents;

import java.util.ArrayList;
import java.util.List;

import tree.IDSearcher;
import tree.TreeNode;

public class BookContents 
{
	private TreeNode<ChapterID> chapterIDTree = new TreeNode<ChapterID>(null);
	private TreeNode<IChapter> chapterContentsTree = new TreeNode<IChapter>(null);
	private IDSearcher<IChapter> chapterContentsIndex = new IDSearcher<IChapter>(null);
	private List<ChapterID> flatIndex = new ArrayList<ChapterID>();
	
	public TreeNode<ChapterID> getChapterIDTree() {
		return chapterIDTree;
	}
	
	public void setChapterIDTree(TreeNode<ChapterID> chapterIDTree) {
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

	public List<ChapterID> getFlatIndex() {
		return flatIndex;
	}

	public void setFlatIndex(List<ChapterID> flatIndex) {
		this.flatIndex = flatIndex;
	}

}
