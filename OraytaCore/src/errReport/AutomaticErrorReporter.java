package errReport;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import book.Book;
import book.contents.ChapterAddress;

public class AutomaticErrorReporter implements IErrReporter
{
	private static String serverHostUrl="http://moshewagner.pythonanywhere.com";
	private static String httpMethod="POST";
	private static String contentType="text/plain";
	
	private HttpURLConnection httpConnection = null;
	private DataOutputStream outToServer=null;

	private String sendMsgToServer(String msg)
	{
		try 
		{
			URL u=new URL(serverHostUrl);
			httpConnection=(HttpURLConnection) u.openConnection();
			httpConnection.setRequestMethod(httpMethod);
			httpConnection.setDoOutput(true);
			httpConnection.setRequestProperty("Content-type",contentType);
			outToServer = new DataOutputStream(httpConnection.getOutputStream());
			
			outToServer.write(msg.getBytes());
			
			return httpConnection.getResponseMessage();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
			return "Error!";
		}
	}
	
	public String sendErrorReport(String title, String body)
	{
		return sendMsgToServer(title + "|" + body);
	}
	
	public String sendErrorReport(Book b, ChapterAddress chapAddr, String msg)
	{
		String title = b.getDisplayName() + " - " + chapAddr.getUID();
		
		String body = "This is a Auto-Generated Orayta Error Report.\n";
		body += "The error seems to be at " + title + "\n\n";
		body += "Error description:\n";
		body += msg;
		
		return sendErrorReport(title, body);
	}
}
