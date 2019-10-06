package org.xarch.reliable.service;

import java.util.Map;
import java.util.Set;

public interface DataWorkServer {
	
	public Map<String, Object> onGetActinfoListByOpenid(String openid);

	public Map<String, Object> onGetActinfoByActid(String actid);
	
	public Map<String, Object> onGetOAManagerList(String openid, String actid);

	public Map<String, Object> onSetOAManagerList(String openid, String actid);
	
	public Map<String, Object> onGetOMList(String openid);
	
	public Map<String, Object> onGetAMList(String actid);

	public Map<String, Object> onSetActinfoByBody(Map<String, Object> data);
	
	public Map<String, Object> onSetActClear(String actid);
	
	public Map<String, Object> onGetActClear(String actid);
	
	public Map<String, Object> onGetActBaoZhengHB(String actid);
	
	public Map<String, Object> onGetActStatus(String actid);
	
	public Map<String, Object> onSetActStatus(String openid, String actid, String status);
	
	public Map<String, Object> onAddActPartNumber(String actid);
	
	public Map<String, Object> onGetPayid();
	
	public Map<String, Object> onSetPayidMap(String actid, String openid, String out_trade_no);
	
	public Map<String, Object> onGetPayidMap(String actid);

	public Map<String, Object> onCheckOAManagerList(String openid, String actid);
	
	public Map<String, Object> onGetOrderTotalFee(String out_trade_no);
	
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
	
	public Map<String, Object> onSetDraftinfo(String key, Map<String, Object> data);
	
	public Map<String, Object> onGetDraftidmap(String openid);
	
	public Map<String, Object> onSetCollectinfo(String key, String actid);
	
	public Map<String, Object> onGetCollectmap(String openid);
	
	public Map<String, Object> onSetBillinfo(String key, String actid, String reliableMoney);
	
	public Map<String, Object> onGetBillinfo(String openid);
	
	public Map<String, Object> onGetCheck(String actid);
	
	public Map<String, Object> onSetCheck(String actid);
	
	public Map<String, Object> onSetFallback(String key, Map<String, Object> data);
	
	public Map<String, Object> getAllactid();

	public Map<String, Object> onDefault();

	
}
