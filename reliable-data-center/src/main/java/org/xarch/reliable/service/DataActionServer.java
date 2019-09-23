package org.xarch.reliable.service;

import java.util.Map;

public interface DataActionServer {
	
	public Map<String, Object> onGetActinfoListByOpenid(String openid);

	public Map<String, Object> onGetActidListByOpenid(String openid);

	public Map<String, Object> onGetOpenidListByActid(String actid);

	public Map<String, Object> onGetActinfoByActid(String actid);

	public Map<String, Object> onSetOpenid2ActidList(String openid, String actid);

	public Map<String, Object> onSetActid2OpenidList(String actid, String openid);

	public Map<String, Object> onSetActinfoByBody(Map<String, Object> data);
	
	public Map<String, Object> onSetActClear(String actid);
	
	public Map<String, Object> onGetActClear(String actid);
	
	public Map<String, Object> onSetActStatus(String openid, String actid, String status);
	
	public Map<String, Object> onAddActPartNumber(String actid);

	public Map<String, Object> onCheckOpenid2ActidList(String openid, String actid);

	public Map<String, Object> onCheckActid2OpenidList(String actid, String openid);
	
	public Map<String, Object> onSetOrderRequest(String key, Map<String, Object> data);
	
	public Map<String, Object> onGetOrderRequest(String key);
	
	public Map<String, Object> onSetOrderResponse(String key, Map<String, Object> data);
	
	public Map<String, Object> onGetOrderResponse(String key);
	
	public Map<String, Object> onSetRefundRequest(String key, Map<String, Object> data);
	
	public Map<String, Object> onGetRefundRequest(String key);
	
	public Map<String, Object> onSetRefundResponse(String key, Map<String, Object> data);
	
	public Map<String, Object> onGetRefundResponse(String key);
	
	public Map<String, Object> onSetOrderNotify(String key, Map<String, Object> data);
	
	public Map<String, Object> onGetOrderNotify(String key);
	
	public Map<String, Object> onSetRefundNotify(String key, Map<String, Object> data);
	
	public Map<String, Object> onGetRefundNotify(String key);

	public Map<String, Object> onDefault();
	
}
