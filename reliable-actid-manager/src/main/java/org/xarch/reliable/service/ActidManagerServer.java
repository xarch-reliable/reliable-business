package org.xarch.reliable.service;

import java.util.Map;

public interface ActidManagerServer {

	public Map<String, String> addActid2Openid(String actid,String openid);
	
	public Map<String, String> updateActid2OpenidCheck(String actid,String openid);
	
	public Map<String, String> getActid2Openid2Map(String actid);
}
