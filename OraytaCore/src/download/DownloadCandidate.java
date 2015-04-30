package download;

import settings.SettingsManager;

public class DownloadCandidate 
{
	private String Category;
	private String URL;
	private int sizeInBytes;
	private String Md5Hash;
	
	public DownloadCandidate(String category, String url, int sizeInBytes, String md5Hash)
	{
		this.Category = category;
		this.URL = url;
		this.sizeInBytes = sizeInBytes;
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
	public String getMd5Hash() {
		return Md5Hash;
	}
	public void setMd5Hash(String md5Hash) {
		Md5Hash = md5Hash;
	}
	
	public String getSaveToPath()
	{
		return getURL().replace(SettingsManager.generalSettings().SERVER_BOOK_ROOT_URL, SettingsManager.generalSettings().BOOKS_SAVE_PATH);
	}
}
