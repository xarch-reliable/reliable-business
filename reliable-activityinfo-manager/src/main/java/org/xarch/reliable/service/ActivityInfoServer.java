package org.xarch.reliable.service;

import java.util.List;
import java.util.Map;

public interface ActivityInfoServer {

	public Map<String, Object> setActivityInfo(String actid, Map<String, Object> actdata);
	
	public Map<String, Object> getActivityInfo(String actid);
	
	public Map<String, Object> getActInfoListByOpenid(String openid);
	////////////////////////
	
	public Map<String, Object> getActInfoListByActidlist(List<Object> actlist);
	
	public Map<String, Object> setActClear(String actid);
	
	public Map<String, Object> getActClear(String actid);
	
	public Map<String, Object> getcheck(String actid);
	
	public Map<String, Object> setcheck(String actid);
	
	public Map<String, Object> getActTotalFee(String actid);
	
	public Map<String, Object> addActPartNumber(String actid);
	
	public Map<String, Object> setActStatus(String openid, String actid, String status);
//////////////////
	public Map<String, Object> getAllactid();
	
}
