package download;

import java.util.List;

public interface ICategoryListReadyListener 
{
	public void onCategoryListReady (List<DownloadCategoryTitle> categories);
}
