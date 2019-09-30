package org.xarch.reliable.service;

import java.util.Map;

public interface DraftidinfoServer {
	
	public Map<String, Object> setCollectinfo(String openid, String actid);

	public Map<String, Object> getCollectinfo(String openid);

}
