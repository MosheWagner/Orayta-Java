package htmlRenderer;

public class HtmlPage 
{
	//TODO: Improve and actually use!
	
	String htmlHead;
	String htmlBody;
	String htmlEnd;
	
	public String getHtmlString()
	{
		return toString();
	}
	
	public String toString()
	{
		return htmlHead + htmlBody + htmlEnd;
	}

	public String getHtmlHead() {
		return htmlHead;
	}

	public void setHtmlHead(String htmlHead) {
		this.htmlHead = htmlHead;
	}

	public String getHtmlBody() {
		return htmlBody;
	}

	public void setHtmlBody(String htmlBody) {
		this.htmlBody = htmlBody;
	}

	public String getHtmlEnd() {
		return htmlEnd;
	}

	public void setHtmlEnd(String htmlEnd) {
		this.htmlEnd = htmlEnd;
	}
}
