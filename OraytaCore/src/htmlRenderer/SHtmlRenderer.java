package htmlRenderer;

import java.util.Collection;

import tree.ITreeIterator;
import tree.TreeNode;

import book.Book;
import book.contents.ChapterID;
import book.contents.IChapter;

public class SHtmlRenderer implements IHtmlRenderer
{

	public String renderBookChapterIndex(Book book) {
		// TODO Auto-generated method stub
		return null;
	}

	public String renderChapter(Book book, ChapterID chapid) 
	{
		String html = HtmlMarkupBuilder.genHeader(book.getDisplayName() + " - " + chapid.getUID());
		
		TreeNode<IChapter> baseNode = book.getContents().getChapterByID(chapid);

		int startLevel = baseNode.data.getChapterAddress().getLevel();
		
		ITreeIterator<TreeNode<IChapter>> iter = baseNode.iterator();
		
		TreeNode<IChapter> chapNode;
		while ( (chapNode = iter.next()) != null)
		{
			//TODO: Improve all this
			
			if (!chapNode.data.equals(baseNode.data))
			{
				if (chapNode.data.getChapterAddress().getLevel() <=  startLevel) break;
			}
			
			IChapter chap = chapNode.data;
			
			//TODO: Add css so that <h6> doesn't line break
			html += HtmlMarkupBuilder.genChapTitle(chap);
			
			//TODO: Have this in base paragraph style
			html += chap.text();
		} 
		
		html += HtmlMarkupBuilder.htmlEnd();
		
		return html;
	}

	public String renderChapterWeaved(Book baseBook,
			Collection<Book> otherBooks, ChapterID chapid) {
		// TODO Auto-generated method stub
		return null;
	}

}
