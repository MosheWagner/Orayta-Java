package download;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.util.Collection;
import java.util.LinkedList;

import fileManager.FileHash;



public class SimpleSingleFileDownloader implements ISingleFileDownloader
{
	Collection<IDownloadListener> listeners = new LinkedList<IDownloadListener>();
	
	String mDownloadPath;
	String mSavePath;
	String origSavePath;
	boolean mOverWrite;
	
	String mHash = null;
	
	DownloadStatus Status = DownloadStatus.NOT_STARTED_YET;
	
	Thread thread = null;

	public void downloadAsyncAndCheck(String urlPath, String filePath, boolean overWrite, String hash)
	{
		mHash = hash;
		downloadAsync(urlPath, filePath, overWrite);
	}
	
	public void downloadAsync(String urlPath, String filePath, boolean overWrite) 
	{
		mDownloadPath = urlPath;
		mSavePath = filePath;
		mOverWrite = overWrite;
		
		thread = new Thread(this);
		thread.start();
		
		Status = DownloadStatus.ACTIVE;
	}
	
	public void run()
	{
		try {
			downloadFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void downloadFile() throws IOException
	{
		addSuffixToFilename();
		
		URL downloadUrl = new URL(mDownloadPath);
		
		File downloadFileOrig = new File(origSavePath);
		File downloadFile = new File(mSavePath);
		if (downloadFileOrig.exists())
		{
			if (mOverWrite == false) return;
			
			//Will throw an IOException if fails
			downloadFileOrig.delete();
		}
		if (downloadFile.exists())
		{
			//Will throw an IOException if fails
			downloadFile.delete();
		}
		
		ReadableByteChannel rbc;
		rbc = Channels.newChannel(downloadUrl.openStream());
		
        FileOutputStream fos = new FileOutputStream(downloadFile);
        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        fos.close();
        rbc.close();
        
        downloadFinished();
	}

	private void downloadFinished()
	{
		try {
			removeSuffixFromFilename();

			Status = DownloadStatus.FINISHED_OK;
			
			File tester = new File(mSavePath);
			if (! tester.exists()) Status = DownloadStatus.FINISHED_ERROR;
			
			//Test for hash only if we got one to compare to
			if (mHash != null)
			{
				if (!FileHash.calculateMd5(mSavePath).equals(mHash))
				{
					Status = DownloadStatus.FINISHED_ERROR;
					tester.delete();
				}
			}
		} catch (IOException e) { Status = DownloadStatus.FINISHED_ERROR; }
		
		postFinished();
	}
	
	private void postFinished()
	{
		//Tell everyone we are done
		for (IDownloadListener l:listeners)
		{
			l.onDownloadFinished(Status);
		}
	}

	//Add ".download" to the filename
	private void addSuffixToFilename() 
	{
		origSavePath = mSavePath;
		mSavePath = mSavePath + ".download";
	}
	
	//Remove the ".download" from the filename
	private void removeSuffixFromFilename() throws IOException  
	{
		File oldFile = new File(mSavePath);
		File newFile = new File(origSavePath);
		Files.move(oldFile.toPath(), newFile.toPath());
		
		mSavePath = origSavePath;
	}

	public void registerProgressListener(IDownloadListener iListener) {
		// TODO Auto-generated method stub

	}

	public void registerFinishedListener(IDownloadListener iListener) {
		listeners.add(iListener);
	}

	public DownloadStatus getStatus() 
	{
		return Status;
	}

	public long getDownloadSize() {
		// TODO Auto-generated method stub
		return -1;
	}

	public int getProgress() {
		// TODO Auto-generated method stub
		return -1;
	}
}
