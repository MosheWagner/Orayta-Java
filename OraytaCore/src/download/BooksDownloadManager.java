package download;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
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
	
	private String SavedBookListPath = SettingsManager.generalSettings().BOOKS_SAVE_PATH + "BookList";
	
	public void downloadBookList()
	{
		ISingleFileDownloader downloader = new ProgressedFileDownload();

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
		
		System.out.println(getAvailableCatagories());
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
				categoryTitle = line.substring(1).trim();
				
				//New category
				files = new ArrayList<DownloadCandidate>();
				
				categories.add(categoryTitle);
				categoryFileMapper.put(categoryTitle, files);
			}
			else if (line.startsWith("."))
			{
				String url = "";
				int sizeInBytes = -1;
				Date date = null;
				String hash = "";
				
				String[] lineParts = line.split(", ");
				
				if (lineParts.length > 2)
				{
					url = lineParts[0].replace("./", SettingsManager.generalSettings().SERVER_BOOK_ROOT_URL);
					
					sizeInBytes = Integer.parseInt(lineParts[1]);
					
					try 
					{
						date = format.parse(lineParts[2]);
					} 
					catch (ParseException e) 
					{
						System.out.println("Couldn't parse date: " + lineParts[2]);
					}
				}
				if (lineParts.length > 3) 
				{
					hash = lineParts[3]; 
				}
				
				DownloadCandidate dc = new DownloadCandidate(categoryTitle, url, sizeInBytes, hash);
				
				if (!isFileUpToDate(dc.getSaveToPath(), date)) files.add(dc);
			}
		}
		
		
		ArrayList<String> categoriesCpy = new ArrayList<String>(categories);
		
		for (String category:categories)
		{
			if (categoryFileMapper.get(category).isEmpty())
			{
				categoryFileMapper.remove(category);
				categoriesCpy.remove(category);
			}
		}
		categories = categoriesCpy;
	}
	
	private Boolean isFileUpToDate(String filePath, Date remoteUpdateDate)
	{	
		return isFileUpToDate(new File(filePath), remoteUpdateDate);
	}
	
	private Boolean isFileUpToDate(File f, Date remoteUpdateDate)
	{	
		if (!f.exists()) return false;
		
		if (remoteUpdateDate == null) return false;
		
		Date d = new Date(f.lastModified());
		if (d.before(remoteUpdateDate)) return false;
		
		return true;
	}
	
	public Collection<String> getAvailableCatagories()
	{
		return categories;
	}
	
	
	
//	public abstract void downloadCatagories(Collection<String> catagoriesToDownload);
//	private abstract void downloadCatagorie(String catagorieToDownload);

}
