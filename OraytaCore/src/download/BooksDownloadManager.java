package download;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.TreeMap;

import download.ISingleFileDownloader.DownloadStatus;
import fileManager.SimplestFileReader;

import settings.SettingsManager;

/*
 * This class should cover all aspects of managing book downloading:
 * 	 Getting the list, parsing it, downloading the books, checking what exists (and is up to date) on the drive, etc'.
 */

public class BooksDownloadManager 
{
	private TreeMap<String, ArrayList<DownloadCandidate>> categoryFileMapper = new TreeMap<String, ArrayList<DownloadCandidate>>();
	private ArrayList<String> categories = new ArrayList<String>();
	
	private String SavedBookListPath = SettingsManager.generalSettings().BOOK_LIST_DOWNLOAD_SAVE_PATH;
	
	public BooksDownloadManager()
	{
		
	}
	
	public void downloadBookList()
	{
		ISingleFileDownloader downloader = new ProgressedFileDownload();
		//ISingleFileDownloader downloader = new SimpleSingleFileDownloader();
		
		downloader.registerFinishedListener(new IDownloadListener() {
			public void onDownloadProgress(int percent) {}
			public void onDownloadFinished(DownloadStatus status) 
			{
				if (status == DownloadStatus.FINISHED_OK) onDownloadBooklistDone();
			}
		});
		
		downloader.downloadAsync(SettingsManager.generalSettings().BOOK_LIST_DOWNLOAD_URL, SavedBookListPath , true);
	}
	
	public void onDownloadBooklistDone()
	{
		readThenParseBookList();
	}
	
	protected void readThenParseBookList()
	{
		try {
			String rawtext = new SimplestFileReader().readContents(SavedBookListPath);
			List<String> lines = Arrays.asList(rawtext.split("\\r?\\n"));
			
			parseBookListContents(lines);
		} 
		catch (FileNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	protected void parseBookListContents(List<String> contentsLines)
	{
		DateFormat format = new SimpleDateFormat("dd/MM/yy");
		
		ArrayList<DownloadCandidate> files = new ArrayList<DownloadCandidate>();
		String categoryTitle = "";
		
		
		for (String line:contentsLines)
		{

			if (line.trim().equals("") || line.startsWith("#"))
			{
				//Do nothing
			}
			else if (line.startsWith("@"))
			{
				categoryTitle = line.substring(2);
				
				//New category
				files = new ArrayList<DownloadCandidate>();
				
				categories.add(categoryTitle);
				categoryFileMapper.put(categoryTitle, files);
			}
			else if (line.startsWith("."))
			{
				String[] lineParts = line.split(", ");
				String url = "";
				int sizeInBytes = -1;
				Date date = null;
				String hash = "";
				
				if (lineParts.length > 2)
				{
					url = lineParts[0];
					sizeInBytes = Integer.parseInt(lineParts[1]);
					try {
						date = format.parse(lineParts[2]);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if (lineParts.length > 3) 
				{
					hash = lineParts[3]; 
				}
				
				//File
				DownloadCandidate dc = new DownloadCandidate(categoryTitle, url, sizeInBytes, date, hash);
				files.add(dc);
			}
		}
	}
	
//	public abstract void downloadCatagories(Collection<String> catagoriesToDownload);
//	public abstract void downloadCatagorie(String catagorieToDownload);
//	
//	public abstract Collection<String> getAvailableCatagories();
//	
//	//public abstract Collection<String> getLocalOutdatedBooks();
//	
//
//	protected abstract Boolean isUpToDate(File f, Date remoteUpdateDate);
}
