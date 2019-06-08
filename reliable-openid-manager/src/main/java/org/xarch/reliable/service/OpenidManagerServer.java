package org.xarch.reliable.service;

import java.util.Map;

public interface OpenidManagerServer {

	public Map<String, String> addOpenid2Actid(String openid, String actid);
	
	public Map<String, String> updateOpenid2ActidCheck(String openid, String actid);
	
	public Map<String, String> getOpenidActid2Map(String openid);
	
}
