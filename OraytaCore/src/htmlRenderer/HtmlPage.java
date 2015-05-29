package htmlRenderer;

public class HtmlPage 
{
	private String htmlHead;
	private String htmlBody;
	private String htmlEnd;
	
	public HtmlPage() {}
	
	public HtmlPage(String savePath, String htmlHead, String htmlBody, String htmlEnd)
	{
		this.htmlHead = htmlHead;
		this.htmlBody = htmlBody;
		this.htmlEnd = htmlEnd;
	}

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
