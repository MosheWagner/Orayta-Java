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
		
		TreeNode<IChapter> chapNode = book.getContents().getChapterByID(chapid);
		
		ITreeIterator<TreeNode<IChapter>> iter = chapNode.iterator();
		
		while (iter.hasNext())
		{
			//TODO: Implement!
			html += iter.next().data.text();
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
