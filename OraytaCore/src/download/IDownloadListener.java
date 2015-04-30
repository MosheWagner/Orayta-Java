package download;

import download.ISingleFileDownloader.DownloadStatus;

public interface IDownloadListener 
{
	public void onDownloadFinished(DownloadStatus status);
	public void onDownloadProgress(int percent);
}
