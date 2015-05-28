package errReport;

import book.Book;
import book.contents.ChapterAddress;

public interface IErrReporter 
{
	public String sendErrorReport(Book b, ChapterAddress chapAddr, String msg);
}
