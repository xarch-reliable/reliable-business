package org.xarch.reliable.sevice;

import java.util.Map;

public interface DraftinfoServer {
	
	public Map<String, Object> setDraftinfo(String draftid, Map<String, Object> data);

	public Map<String, Object> getDraftinfo(String draftid);
	
	
	

}
