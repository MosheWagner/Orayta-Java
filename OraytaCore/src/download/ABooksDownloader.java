package download;

import java.io.File;
import java.util.Collection;

/*
 * Abstract for declaration purposes only.
 * This class should cover all aspects of managing book downloading:
 * 	 Getting the list, parsing it, downloading the books, checking what exists (and is up to date) on the drive, etc'.
 */

public abstract class ABooksDownloader 
{
	public abstract void downloadBookList();
	protected abstract void parseBookList();
	
	public abstract void downloadCatagories(Collection<String> catagoriesToDownload);
	
	
	public abstract Collection<String> getAvailableCatagories();
	

	
	protected abstract Boolean checkHash(File f, String MD5Hash);
}
