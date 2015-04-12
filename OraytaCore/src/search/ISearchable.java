package search;

public interface ISearchable 
{
	public <Collection> ISearchResult search(ISearchQuery query);
}
