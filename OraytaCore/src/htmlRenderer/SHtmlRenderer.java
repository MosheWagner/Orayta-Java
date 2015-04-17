package htmlRenderer;

import java.util.ArrayList;
import java.util.Arrays;
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
		
		TreeNode<IChapter> baseNode = book.getContents().getChapterByID(chapid.getUID());

		if (baseNode == null) return "Invalid chapter!";
		
		int startLevel = baseNode.data.getChapterAddress().getLevel();
		int sameLevelCount = -1; //-1 Because we are going to count this chapter once too
		
		ITreeIterator<TreeNode<IChapter>> iter = baseNode.iterator();
		
		TreeNode<IChapter> chapNode;
		while ((chapNode = iter.next()) != null)
		{
			if (chapNode.data.getChapterAddress().getLevel() <= startLevel) sameLevelCount ++;
			if (sameLevelCount > 0) break;
			
			IChapter chap = chapNode.data;
			
			ArrayList<String> chapLines = new ArrayList<String>(Arrays.asList(chap.text().split("\\r?\\n")));
			
			//Deal with low-level chap markers (only if they come at the beginning of the chapter)
			if (chapLines.size() > 1 && chapLines.get(0).trim().startsWith(HtmlMarkupBuilder.genHtmlMarkerComment()))
			{
				//TODO: This is generated when OBK built. Probably should move here
				html += chapLines.get(1);
				
				chapLines.remove(1);
				chapLines.remove(0);
			}
			
			html += HtmlMarkupBuilder.genChapTitle(chap);
			
			//TODO: Add css so that <h6> doesn't line break
			//TODO: Have this in base paragraph style
			//TODO: Add <BR>?
			for (String line:chapLines) html += line + "\n";
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
