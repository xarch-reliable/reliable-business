package org.xarch.reliable.sevice;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface DraftinfoServer {
	
	public Map<String, Object> setDraftinfo(String draftid, Map<String, Object> data);

	public Map<String, Object> getDraftinfo(List<String> draftidlist);
	
	
	

}
