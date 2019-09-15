package org.xarch.reliable.service;

import java.util.Map;

public interface DataDealServer {
	
	public Map<String, Object> execute(String RequestStr);
	
	public Map<String, Object> dispatch(String openid, Map<String, Object> bodyMap, Map<String, Object> responseMap);
	
}
