package org.xarch.reliable.service;

import java.util.Map;

public interface DraftidinfoServer {
	public Map<String, Object> setDraftidinfo(String openid, String draftid, Map<String, Object> data);

	public Map<String, Object> getDraftidinfo(String openid);

}
