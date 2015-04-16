package download;

/*
 * Downloads a single file. 
 * NOTE: Implementations of this class block execution until the download finishes, so they must be called in a new thread 
 */

public interface ISingleFileDownloader extends Runnable
{
	public enum DownloadStatus
	{
		NOT_STARTED_YET, ACTIVE, FINISHED_OK, FINISHED_ERROR/*, PUASED, CANCELED*/;
	}
	
	public void downloadNewThread(String urlPath, String filePath, boolean overWrite);
	
	public void registerProgressListener(IDownloadListener listener);
	public void registerFinishedListener(IDownloadListener listener);
	
	public DownloadStatus getStatus();
	public int getProgress();
	public long getDownloadSize();
}
