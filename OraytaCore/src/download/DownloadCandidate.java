package download;

import java.util.Date;

import settings.SettingsManager;

public class DownloadCandidate 
{
	private String Category;
	private String URL;
	private int sizeInBytes;
	private String Md5Hash;
	private Date mDate;
	
	public DownloadCandidate(String category, String url, int sizeInBytes, String md5Hash, Date date)
	{
		this.Category = category;
		this.URL = url;
		this.sizeInBytes = sizeInBytes;
		this.Md5Hash = md5Hash;
		this.mDate = date;
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
	public String getMd5Hash() {
		return Md5Hash;
	}
	public void setMd5Hash(String md5Hash) {
		Md5Hash = md5Hash;
	}
	
	public String getSaveToPath()
	{
		return getURL().replace(SettingsManager.getSettings().get_SERVER_BOOK_ROOT_URL(), SettingsManager.getSettings().get_BOOKS_SAVE_PATH());
	}

	public Date getDate() {
		return mDate;
	}

	public void setDate(Date mDate) {
		this.mDate = mDate;
	}
}
