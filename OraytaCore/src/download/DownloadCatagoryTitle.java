package download;

public class DownloadCatagoryTitle
{
	private String CatagoryName;
	private double sizeInMB;
	
	public String toString()
	{
		return CatagoryName + " (" + sizeInMB + " MB)";
	}
	
	public static DownloadCatagoryTitle fromString(String displayStr)
	{
		DownloadCatagoryTitle dct = new DownloadCatagoryTitle();
		
		int ind = displayStr.indexOf(" (");
		int ind2 = displayStr.indexOf(" MB)");
		
		dct.setCatagoryName(displayStr.substring(0, ind));
		
		double size = Double.valueOf(displayStr.substring(ind + 2, ind2));
		dct.setSizeInBytes(size);
		
		return dct;
	}

	public String getCatagoryName() {
		return CatagoryName;
	}

	public void setCatagoryName(String catagoryName) {
		CatagoryName = catagoryName;
	}

	public double getSizeInBytes() {
		return sizeInMB;
	}

	public void setSizeInBytes(double sizeInMB) {
		this.sizeInMB = sizeInMB;
	}
}
