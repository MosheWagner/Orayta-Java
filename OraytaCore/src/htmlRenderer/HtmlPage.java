package htmlRenderer;

public class HtmlPage 
{
	//TODO: Improve and actually use!
	
	String htmlHead;
	String CSS;
	String htmlBody;
	
	public String toString()
	{
		return htmlHead + htmlBody;
	}

	public String getHtmlHead() {
		return htmlHead;
	}

	public void setHtmlHead(String htmlHead) {
		this.htmlHead = htmlHead;
	}

	public String getCSS() {
		return CSS;
	}

	public void setCSS(String cSS) {
		CSS = cSS;
	}

	public String getHtmlBody() {
		return htmlBody;
	}

	public void setHtmlBody(String htmlBody) {
		this.htmlBody = htmlBody;
	}
}
