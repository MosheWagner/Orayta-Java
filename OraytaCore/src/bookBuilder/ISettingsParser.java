package bookBuilder;

import java.util.List;
import java.util.Map;

public interface ISettingsParser 
{
	public Map<String, String> buildSettingMap();
	public List<String[]> buildWeavedDiplaySourcesList();
}
