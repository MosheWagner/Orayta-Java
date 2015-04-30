package download;

public class DownloadCategoryTitle
{
	private String CategoryName;
	private double sizeInMB;
	
	public DownloadCategoryTitle() { }
	
	public DownloadCategoryTitle(String catagoryName, double sizeInMB)
	{
		CategoryName = catagoryName;
		this.sizeInMB = sizeInMB;
	}

	public String toString()
	{
		return CategoryName + " (" + round(sizeInMB, 2) + " MB)";
	}
	
	private double round(double n, int digitsAfterZero)
	{
		double exp = Math.pow(10, digitsAfterZero);
		
		n = n * exp;
		n = Math.round(n);
		
		return n / exp;
	}
	
	public static DownloadCategoryTitle fromString(String displayStr)
	{
		DownloadCategoryTitle dct = new DownloadCategoryTitle();
		
		int ind = displayStr.indexOf(" (");
		int ind2 = displayStr.indexOf(" MB)");
		
		dct.setCategoryName(displayStr.substring(0, ind));
		
		double size = Double.valueOf(displayStr.substring(ind + 2, ind2));
		dct.setSizeInMB(size);
		
		return dct;
	}

	public String getCategoryName() {
		return CategoryName;
	}

	public void setCategoryName(String categoryName) {
		CategoryName = categoryName;
	}

	public double getSizeInMB() {
		return sizeInMB;
	}

	public void setSizeInMB(double sizeInMB) {
		this.sizeInMB = sizeInMB;
	}
}
