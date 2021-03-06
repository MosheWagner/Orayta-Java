package download;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.util.Collection;
import java.util.LinkedList;

import fileManager.FileHash;

//TODO: Implement pause and cancel

public class ProgressedFileDownload implements ISingleFileDownloader
{

	Collection<IDownloadListener> progressListeners = new LinkedList<IDownloadListener>();
	Collection<IDownloadListener> finishedListeners = new LinkedList<IDownloadListener>();
	
	Thread thread = null;
	
	String mDownloadPath;
	String mSavePath;
	String origSavePath;
	boolean mOverWrite;
	
	String mHash = null;
	
	DownloadStatus Status = DownloadStatus.NOT_STARTED_YET;
	
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
	}


	public void run() {
		download();
	}
	
	// Max size of download buffer.
	private static final int MAX_BUFFER_SIZE = 1024;
    int size = -1;
    int downloaded = 0;
    
	public void download()
	{
		addSuffix();
		
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
		
		//Make sure path exists:
		downloadFile.getParentFile().mkdirs();
		
		RandomAccessFile file = null;
		InputStream stream = null;
		
	    try {
	    	Status = DownloadStatus.ACTIVE;
	    	
	    	URL url = new URL(mDownloadPath);
	    	
	        // Open connection to URL.
	        HttpURLConnection connection =
	                (HttpURLConnection) url.openConnection();

	        // Specify what portion of file to download.
	        connection.setRequestProperty("Range",
	                "bytes=" + downloaded + "-");

	        // Connect to server.
	        connection.connect();

	        // Make sure response code is in the 200 range.
	        if (connection.getResponseCode() / 100 != 2) {
	            error();
	        }

	        // Check for valid content length.
	        int contentLength = connection.getContentLength();
	        if (contentLength < 1) {
	            error();
	        }

	        /* Set the size for this download if it
	     	hasn't been already set. */
	        if (size == -1) {
	            size = contentLength;
	        }
	    
	        // Open file and seek to the end of it.
	        file = new RandomAccessFile(mSavePath, "rw");
	        file.seek(downloaded);
	
	        stream = connection.getInputStream();
	        while (Status == DownloadStatus.ACTIVE) 
	        {
	        	/* Size buffer according to how much of the
	       		file is left to download. */
	        	
	            byte buffer[];
	            if (size - downloaded > MAX_BUFFER_SIZE) 
	            {
	                buffer = new byte[MAX_BUFFER_SIZE];
	            } 
	            else 
	            {
	                buffer = new byte[size - downloaded];
	            }
	
	            // Read from server into buffer.
	            int read = stream.read(buffer);
	            if (read == -1)
	                break;
	
	            // Write buffer to file.
	            file.write(buffer, 0, read);
	            downloaded += read;
	            
	            updateProgress();
	        }
	        downloadFinished();
	    }
	    catch (Exception e) {
            error();
        } finally {
            // Close file.
            if (file != null) {
                try {
                    file.close();
                } catch (Exception e) {}
            }

            // Close connection to server.
            if (stream != null) {
                try {
                    stream.close();
                } catch (Exception e) {}
            }
        }

	}

	private void error()
	{
		Status = DownloadStatus.FINISHED_ERROR;
	}
	    
	public void registerProgressListener(IDownloadListener listener) {
		progressListeners.add(listener);
	}

	public void registerFinishedListener(IDownloadListener listener) {
		finishedListeners.add(listener);
	}

	public DownloadStatus getStatus() 
	{
		return Status;
	}
	
	// Get this download's size in bytes.
	public long getDownloadSize() {
	    return size;
	}

	// Get this download's progress.
	public int getProgress() {
	    return (int) (((float) downloaded / size) * 100);
	}
	
	private void updateProgress()
	{
		//Tell everyone what our progress is
		for (IDownloadListener l:progressListeners)
		{
			l.onDownloadProgress(getProgress());
		}
	}
	
	private void downloadFinished(){
		try {
			removeSuffix();

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
		for (IDownloadListener l:finishedListeners)
		{
			l.onDownloadFinished(Status);
		}
	}
	
	private void addSuffix() {
		//Add ".download" to the filename
		origSavePath = mSavePath;
		mSavePath = mSavePath + ".download";
	}
	
	private void removeSuffix() throws IOException  
	{
		//Remove the ".download" from the filename
		File oldFile = new File(mSavePath);
		File newFile = new File(origSavePath);
		Files.move(oldFile.toPath(), newFile.toPath());
		
		mSavePath = origSavePath;
	}
}
