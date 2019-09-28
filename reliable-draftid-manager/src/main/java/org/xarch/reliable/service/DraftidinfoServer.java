package org.xarch.reliable.service;

import java.util.Map;
import java.util.Set;

public interface DraftidinfoServer {
	public Map<String, Object> setDraftidinfo(String openid, String draftid, Map<String, Object> data);

	public Map<String, Object> getDraftidinfo(String openid);

}
