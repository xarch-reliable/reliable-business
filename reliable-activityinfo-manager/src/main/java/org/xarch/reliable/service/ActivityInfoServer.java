package org.xarch.reliable.service;

import java.util.Map;

public interface ActivityInfoServer {

	public boolean setActivityInfo(String actid, Map<String, Object> actdata);
	
	public Map<String, Object> getActivityInfo(String actid);
	
	public Map<String, Object> getActInfoListByOpenid(String openid);
	
	public boolean setActClear(String actid);
	
}
