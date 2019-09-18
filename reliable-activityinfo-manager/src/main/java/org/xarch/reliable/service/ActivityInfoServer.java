package org.xarch.reliable.service;

import java.util.Map;

public interface ActivityInfoServer {

	public String setActivityInfo(String actid, Map<String, Object> actdata);
	
	public Map<String, Object> getActivityInfo(String actid);
	
	public Map<String, Object> getActInfoListByOpenid(String openid);
	
	public String setActClear(String actid);
	
	public String getActClear(String actid);
	
	public String addActPartNumber(String actid);
	
	public String setActStatus(String actid, String status);
	
}
