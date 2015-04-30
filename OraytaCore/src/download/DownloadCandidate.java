package download;

import java.util.Date;

public class DownloadCandidate 
{
	private String Category;
	private String URL;
	private int sizeInBytes;
	private Date date;
	private String Md5Hash;
	
	public DownloadCandidate(String category, String url, int sizeInBytes, Date date, String md5Hash)
	{
		this.Category = category;
		this.URL = url;
		this.sizeInBytes = sizeInBytes;
		this.date = date;
		this.Md5Hash = md5Hash;
	}
	
	public String getCategory() {
		return Category;
	}
	public void setCategory(String category) {
		Category = category;
	}
	public String getURL() {
		return URL;
	}
	public void setURL(String uRL) {
		URL = uRL;
	}
	public int getSizeInBytes() {
		return sizeInBytes;
	}
	public void setSizeInBytes(int sizeInBytes) {
		this.sizeInBytes = sizeInBytes;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getMd5Hash() {
		return Md5Hash;
	}
	public void setMd5Hash(String md5Hash) {
		Md5Hash = md5Hash;
	}
}
