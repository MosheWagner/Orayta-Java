package userBookSettings;

import java.util.ArrayList;
import java.util.List;

public class BookSettings 
{
	private Boolean showNikud = true;
	private Boolean showTeamim = true;
	
	List <Integer> weavedDisplayIDs = new ArrayList <Integer>();
	List <String> weavedDisplayTitles = new ArrayList <String>();

	public List<String> getWeavedDisplayTitles() {
		return weavedDisplayTitles;
	}

	public void setWeavedDisplayTitles(List<String> weavedDisplayTitles) {
		this.weavedDisplayTitles = weavedDisplayTitles;
	}

	public Boolean getShowNikud() {
		return showNikud;
	}

	public void setShowNikud(Boolean showNikud) {
		this.showNikud = showNikud;
	}

	public Boolean getShowTeamim() {
		return showTeamim;
	}

	public void setShowTeamim(Boolean showTeamim) {
		this.showTeamim = showTeamim;
	}

	public List<Integer> getWeavedDisplayIDs() {
		return weavedDisplayIDs;
	}

	public void setWeavedDisplayIDs(List<Integer> weavedDisplayIDToShow) {
		this.weavedDisplayIDs = weavedDisplayIDToShow;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((showNikud == null) ? 0 : showNikud.hashCode());
		result = prime * result
				+ ((showTeamim == null) ? 0 : showTeamim.hashCode());
		result = prime
				* result
				+ ((weavedDisplayIDs == null) ? 0 : weavedDisplayIDs
						.hashCode());
		result = prime
				* result
				+ ((weavedDisplayTitles == null) ? 0 : weavedDisplayTitles
						.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BookSettings other = (BookSettings) obj;
		if (showNikud == null) {
			if (other.showNikud != null)
				return false;
		} else if (!showNikud.equals(other.showNikud))
			return false;
		if (showTeamim == null) {
			if (other.showTeamim != null)
				return false;
		} else if (!showTeamim.equals(other.showTeamim))
			return false;
		if (weavedDisplayIDs == null) {
			if (other.weavedDisplayIDs != null)
				return false;
		} else if (!weavedDisplayIDs.equals(other.weavedDisplayIDs))
			return false;
		if (weavedDisplayTitles == null) {
			if (other.weavedDisplayTitles != null)
				return false;
		} else if (!weavedDisplayTitles.equals(other.weavedDisplayTitles))
			return false;
		return true;
	}


}
