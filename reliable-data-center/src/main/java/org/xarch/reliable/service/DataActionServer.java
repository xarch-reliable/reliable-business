package org.xarch.reliable.service;

import java.util.Map;

public interface DataActionServer {
	
	public Map<String, Object> onGetActinfoListByOpenid(String openid);

	public Map<String, String> onGetActidListByOpenid(String openid);

	public Map<String, String> onGetOpenidListByActid(String actid);

	public Map<String, Object> onGetActinfoByActid(String actid);

	public Map<String, Object> onSetOpenid2ActidList(String openid, String actid);

	public Map<String, Object> onSetActid2OpenidList(String actid, String openid);

	public Map<String, Object> onSetActinfoByBody(String actid, Map<String, String> data);

	public Map<String, Object> onCheckOpenid2ActidList(String openid, String actid);

	public Map<String, Object> onCheckActid2OpenidList(String actid, String openid);

	public String onDefault();
	
}
