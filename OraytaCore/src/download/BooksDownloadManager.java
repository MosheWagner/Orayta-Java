package download;

import java.io.File;
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
	private ArrayList<DownloadCategoryTitle> categories = new ArrayList<DownloadCategoryTitle>();
	
	private String SavedBookListPath = SettingsManager.generalSettings().BOOKS_SAVE_PATH + "BookList";
	
	private List<ICategoryListReadyListener> listeners = new ArrayList<ICategoryListReadyListener>();
	
	public void downloadBookList()
	{
		ISingleFileDownloader downloader = new ProgressedFileDownload();

		downloader.registerFinishedListener(new IDownloadListener() {
			public void onDownloadProgress(int percent) {}
			public void onDownloadFinished(DownloadStatus status) 
			{
				if (status == DownloadStatus.FINISHED_OK) onDownloadBooklistDone();
				else System.out.println("Couldn't download book list!");
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
		try 
		{
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
		
		postListReadyEvent();
	}
	
	public void registerToListReadyEvent(ICategoryListReadyListener listener) { listeners.add(listener); }
	
	private void postListReadyEvent() 
	{
		for (ICategoryListReadyListener listener:listeners)
		{
			listener.onCategoryListReady(categories);
		}
	}

	protected void parseBookListContents(List<String> contentsLines)
	{
		ArrayList<DownloadCandidate> files = new ArrayList<DownloadCandidate>();
		DownloadCategoryTitle categoryTitle = null;
		
		for (String line:contentsLines)
		{
			if (line.trim().equals("") || line.startsWith("#")) 
			{ 
				//Do nothing 
			}
			else if (line.startsWith("@"))
			{
				categoryTitle = new DownloadCategoryTitle(); 
				categoryTitle.setCategoryName(line.substring(1).trim());
				
				//New category
				files = new ArrayList<DownloadCandidate>();
				
				categories.add(categoryTitle);
				categoryFileMapper.put(categoryTitle.getCategoryName(), files);
			}
			else if (line.startsWith("."))
			{
				if (categoryTitle != null)
				{
					DownloadCandidate dc = genDownloadCandidateFromLine(categoryTitle.getCategoryName(), line);
				
					if (!isFileUpToDate(dc.getSaveToPath(), dc.getDate())) files.add(dc);
				}
			}
		}
		
		removeStubCategories();
		calculateCategoriesSizes();
	}
	
	private DownloadCandidate genDownloadCandidateFromLine(String categoryTitle, String line)
	{
		DateFormat format = new SimpleDateFormat("dd/MM/yy");
		
		String url = "";
		int sizeInBytes = -1;
		String hash = "";
		//One very old date
		Date date = new Date(0);
		
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
		
		DownloadCandidate dc = new DownloadCandidate(categoryTitle, url, sizeInBytes, hash, date);
		
		return dc;
	}
	
	private void removeStubCategories()
	{
		ArrayList<DownloadCategoryTitle> categoriesCpy = new ArrayList<DownloadCategoryTitle>(categories);
		
		for (DownloadCategoryTitle category:categories)
		{
			if (categoryFileMapper.get(category.getCategoryName()).isEmpty())
			{
				categoryFileMapper.remove(category);
				categoriesCpy.remove(category);
			}
		}
		categories = categoriesCpy;
	}
	
	
	private static double MB_BYTE_COUNT = 1024 * 1024;
	private void calculateCategoriesSizes()
	{
		for (DownloadCategoryTitle category:categories)
		{
			double sizeInMB = 0;
			
			for (DownloadCandidate candidate:categoryFileMapper.get(category.getCategoryName()))
			{
				sizeInMB += candidate.getSizeInBytes() / MB_BYTE_COUNT;
			}
			
			category.setSizeInMB(sizeInMB);
		}
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
	
	public List<DownloadCategoryTitle> getAvailableCatagories()
	{
		return categories;
	}
	
	public void startCategoryDownload(String categoryToDownload)
	{
		ArrayList <DownloadCandidate> filesToDownload = categoryFileMapper.get(categoryToDownload);
		
		DownloadManager dm = new DownloadManager();
		
		for (DownloadCandidate file:filesToDownload)
		{
			dm.addDownloadRequest(file.getURL(), file.getSaveToPath());
		}
	}
	
//	public abstract void downloadCatagories(Collection<String> catagoriesToDownload);
//	private abstract void downloadCatagorie(String catagorieToDownload);

}
