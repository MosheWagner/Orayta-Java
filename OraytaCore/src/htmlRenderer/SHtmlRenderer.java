package htmlRenderer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import htmlRenderer.DCSSBuilder;

import tree.TreeNode;

import book.Book;
import book.contents.ChapterAddress;
import book.contents.IChapter;

public class SHtmlRenderer implements IHtmlRenderer
{

	public String renderBookChapterIndex(Book book) {
		// TODO Auto-generated method stub
		return null;
	}

	public String renderChapter(Book book, ChapterAddress chapid) 
	{
		String html = HtmlMarkupBuilder.genHeader(book.getDisplayName() + " - " + chapid.getUID(),
				new DCSSBuilder(book));
		
		TreeNode<IChapter> baseNode = book.getContents().getChapterNodeByID(chapid.getUID());

		if (baseNode == null) return "Invalid chapter!";
		
		Collection<IChapter> chaps = baseNode.deepSiblingsList();

		for (IChapter chap:chaps)
		{
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
			Collection<Book> otherBooks, ChapterAddress chapid) {
		// TODO Auto-generated method stub
		return null;
	}


}
