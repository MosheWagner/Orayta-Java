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
	public String renderChapter(Book book, ChapterAddress chapid) 
	{
		return renderChapter(book, chapid, new ArrayList<Book>()); 
	}
	
	public String renderChapter(Book book, ChapterAddress chapid, Collection<Book> otherBooks) 
	{
		String html = HtmlMarkupBuilder.genHeader(book.getDisplayName() + " - " + chapid.getTitle(),
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
			
			//TODO: Have this in base paragraph style
			//TODO: Add <BR>?
			for (String line:chapLines) html += line + "\n";
			
			//Add contents from all books we are supposed to weave in
			int c = 0;
			for (Book other:otherBooks)
			{
				IChapter otherChap = other.getContents().getChapterByID(chap.getUID());
				String chapTextNoMarkers = removeChapMarker(otherChap);

				if (!chapTextNoMarkers.isEmpty())
				{
                    if (otherBooks.size() > 1 && c == 0)
                        html += "<BR>\n";
					
					html += "<BR>\n" + HtmlMarkupBuilder.genSpanClassPrefix("W", c++);
					html += HtmlMarkupBuilder.genItalicBold(other.getDisplayName()) + "<BR>\n";
					
					html += chapTextNoMarkers;
					
					html += HtmlMarkupBuilder.genSpanSuffix() + "\n";
					
                    if (otherBooks.size() > 1)
                        html += "<BR>\n";
				}
			}
		}
		
		html += HtmlMarkupBuilder.htmlEnd();
		
		return html;
	}
	
	private String removeChapMarker(IChapter chap)
	{
		if (chap == null) return "";
		
		String chapText = chap.text();
		ArrayList<String> chapLines = new ArrayList<String>(Arrays.asList(chapText.trim().split("\\r?\\n")));
		
		//Deal with low-level chap markers (only if they come at the beginning of the chapter)
		if (chapLines.size() > 1 && chapLines.get(0).trim().startsWith(HtmlMarkupBuilder.genHtmlMarkerComment()))
		{
			chapLines.remove(1);
			chapLines.remove(0);
		}
		
		StringBuilder builder = new StringBuilder();
		for(String s : chapLines) 
		{
			builder.append(s); 
			builder.append("\n"); 
		}
		
		String merged = "";
		if (builder.length() > 0) merged = builder.substring(0, builder.length()-1);
		
		return merged;
	}
}
