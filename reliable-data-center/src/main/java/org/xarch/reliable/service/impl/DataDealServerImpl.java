package org.xarch.reliable.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xarch.reliable.config.event.ReliableDataType;
import org.xarch.reliable.service.DataActionServer;
import org.xarch.reliable.service.DataDealServer;

@Service
public class DataDealServerImpl implements DataDealServer {
	
	private static final Logger logger = LoggerFactory.getLogger(DataDealServerImpl.class);
	
	@Autowired
	private DataActionServer dataActionServer;

	@SuppressWarnings({ "unchecked" })
	@Override
	public Map<String, Object> execute(Map<String, Object> data) {
		Map<String, Object> responseMap = new HashMap<String, Object>();
		String xrdataction = (String)data.get("xrdataction");
		if (xrdataction == null) {
			responseMap.put("error_msg", "数据识别失败");
			return responseMap;
		}
		if (data.get("error_msg") != null) {
			responseMap.put("error_msg", data.get("error_msg"));
			return data;
		}
		return dispatch(xrdataction, (Map<String, Object>)data.get("data"), responseMap);
	}

	@Override
	public Map<String, Object> dispatch(String xrdataction, Map<String, Object> bodyMap, Map<String, Object> responseMap) {

		logger.info("DataDealServerImpl::dispatch() : xraction = " + xrdataction);
		
		ReliableDataType msgType = ReliableDataType.valueOf(xrdataction);
		switch (msgType) {
		case getActinfoListByOpenid:
			logger.info("DataDealServerImpl::dispatch() : getActinfoListByOpenid!!!");
			
			responseMap.put("body", dataActionServer.onGetActinfoListByOpenid((String)bodyMap.get("openid")));
			break;
		case getActinfoByActid:
			responseMap.put("body", dataActionServer.onGetActinfoByActid((String)bodyMap.get("actid")));
			break;
		case getOAManagerList:
			responseMap.put("body", dataActionServer.onGetOAManagerList((String)bodyMap.get("openid"), (String)bodyMap.get("actid")));
			break;
		case setOAManagerList:
			responseMap.put("body", dataActionServer.onSetOAManagerList((String)bodyMap.get("openid"), (String)bodyMap.get("actid")));
			break;
		case getOMList:
			responseMap.put("body", dataActionServer.onGetOMList((String)bodyMap.get("openid")));
			break;
		case getAMList:
			responseMap.put("body", dataActionServer.onGetAMList((String)bodyMap.get("actid")));
			break;
		case setActinfoByBody:
			responseMap.put("body", dataActionServer.onSetActinfoByBody(bodyMap));
			break;
		case setactclear:
			responseMap.put("body", dataActionServer.onSetActClear((String)bodyMap.get("actid")));
			break;
		case getactclear:
			responseMap.put("body", dataActionServer.onGetActClear((String)bodyMap.get("actid")));
			break;
		case setactstatus:
			responseMap.put("body", dataActionServer.onSetActStatus((String)bodyMap.get("openid"), (String)bodyMap.get("actid"), (String)bodyMap.get("status")));
			break;
		case addactpartnumber:
			responseMap.put("body", dataActionServer.onAddActPartNumber((String)bodyMap.get("actid")));
			break;
		case getpayid:
			responseMap.put("body", dataActionServer.onGetPayid());
			break;
		case setpayidMap:
			responseMap.put("body", dataActionServer.onSetPayidMap((String)bodyMap.get("actid"), (String)bodyMap.get("openid"), (String)bodyMap.get("out_trade_no")));
			break;
		case getpayidMap:
			responseMap.put("body", dataActionServer.onGetPayidMap((String)bodyMap.get("actid")));
			break;
		case checkOAManagerList:
			responseMap.put("body", dataActionServer.onCheckOAManagerList((String)bodyMap.get("openid"), (String)bodyMap.get("actid")));
			break;
		case setOrderRequest:
			responseMap.put("body", dataActionServer.onSetOrderRequest((String)bodyMap.get("out_trade_no"), bodyMap));
			break;
		case getOrderRequest:
			responseMap.put("body", dataActionServer.onGetOrderRequest((String)bodyMap.get("out_trade_no")));
			break;
		case setOrderResponse:
			responseMap.put("body", dataActionServer.onSetOrderResponse((String)bodyMap.get("prepay_id"), bodyMap));
			break;
		case getOrderResponse:
			responseMap.put("body", dataActionServer.onGetOrderResponse((String)bodyMap.get("prepay_id")));
			break;
		case setRefundRequest:
			responseMap.put("body", dataActionServer.onSetRefundRequest((String)bodyMap.get("out_refund_no"), bodyMap));
			break;
		case getRefundRequest:
			responseMap.put("body", dataActionServer.onGetRefundRequest((String)bodyMap.get("out_refund_no")));
			break;
		case setRefundResponse:
			responseMap.put("body", dataActionServer.onSetRefundResponse((String)bodyMap.get("out_refund_no"), bodyMap));
			break;
		case getRefundResponse:
			responseMap.put("body", dataActionServer.onGetRefundResponse((String)bodyMap.get("out_refund_no")));
			break;
		case setOrderNotify:
			responseMap.put("body", dataActionServer.onSetOrderNotify((String)bodyMap.get("out_trade_no"), bodyMap));
			break;
		case getOrderNotify:
			responseMap.put("body", dataActionServer.onGetOrderNotify((String)bodyMap.get("out_trade_no")));
			break;
		case setRefundNotify:
			responseMap.put("body", dataActionServer.onSetRefundNotify((String)bodyMap.get("out_refund_no"), bodyMap));
			break;
		case getRefundNotify:
			responseMap.put("body", dataActionServer.onGetRefundNotify((String)bodyMap.get("out_refund_no")));
			break;
		case setDraftinfo:
			responseMap.put("body", dataActionServer.onSetDraftinfo((String)bodyMap.get("draftid"), bodyMap));
			break;
		case getDraftidmap:
			responseMap.put("body", dataActionServer.onGetDraftidmap((String)bodyMap.get("openid")));
			break;
			
		default:
			responseMap.put("body", dataActionServer.onDefault());
			break;
		}
		responseMap.put("xrdataction", msgType);
		return responseMap;
	}

}
