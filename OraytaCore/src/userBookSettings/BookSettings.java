package userBookSettings;

import java.util.ArrayList;
import java.util.Collection;

public class BookSettings 
{

	Boolean showNikud = true;
	Boolean showTeamim = true;
	
	Collection <String> showWeavedDisplay = new ArrayList <String>();

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

	public Collection<String> getShowWeavedDisplay() {
		return showWeavedDisplay;
	}

	public void setShowWeavedDisplay(Collection<String> showWeavedDisplay) {
		this.showWeavedDisplay = showWeavedDisplay;
	}
}
