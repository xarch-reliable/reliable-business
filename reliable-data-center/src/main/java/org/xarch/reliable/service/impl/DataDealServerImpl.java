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
import org.xarch.reliable.utils.BaseResultTools;

@Service
public class DataDealServerImpl implements DataDealServer {
	
	private static final Logger logger = LoggerFactory.getLogger(DataDealServerImpl.class);
	
	@Autowired
	private DataActionServer dataActionServer;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Map<String, Object> execute(String RequestStr) {
		Map request = BaseResultTools.fromJSON(RequestStr, Map.class);
		Map<String, Object> responseMap = new HashMap<String, Object>();
		String xrdataction = (String)request.get("xrdataction");
		if (xrdataction == null) {
			responseMap.put("error_msg", "数据识别失败");
			return responseMap;
		}
		if (request.get("error_msg") != null) {
			responseMap.put("error_msg", request.get("error_msg"));
			return request;
		}
		return dispatch(xrdataction, (Map<String, Object>)request.get("data"), responseMap);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> dispatch(String xrdataction, Map<String, Object> bodyMap, Map<String, Object> responseMap) {

		logger.info("DataDealServerImpl::dispatch() : xraction = " + xrdataction);
		
		ReliableDataType msgType = ReliableDataType.valueOf(xrdataction);
		switch (msgType) {
		case getActinfoListByOpenid:
			responseMap.put("body", dataActionServer.onGetActinfoListByOpenid((String)bodyMap.get("openid")));
			break;
		case getActidListByOpenid:
			responseMap.put("body", dataActionServer.onGetActidListByOpenid((String)bodyMap.get("openid")));
			break;
		case getOpenidListByActid:
			responseMap.put("body", dataActionServer.onGetOpenidListByActid((String)bodyMap.get("actid")));
			break;
		case getActinfoByActid:
			responseMap.put("body", dataActionServer.onGetActinfoByActid((String)bodyMap.get("actid")));
			break;
		case setOpenid2ActidList:
			responseMap.put("body", dataActionServer.onSetOpenid2ActidList((String)bodyMap.get("openid"), (String)bodyMap.get("actid")));
			break;
		case setActid2OpenidList:
			responseMap.put("body", dataActionServer.onSetActid2OpenidList((String)bodyMap.get("actid"), (String)bodyMap.get("openid")));
			break;
		case setActinfoByBody:
			responseMap.put("body", dataActionServer.onSetActinfoByBody((String)bodyMap.get("actid"), (Map<String, String>) bodyMap.get("data")));
			break;
		case checkOpenid2ActidList:
			responseMap.put("body", dataActionServer.onCheckOpenid2ActidList((String)bodyMap.get("openid"), (String)bodyMap.get("actid")));
			break;
		case checkActid2OpenidList:
			responseMap.put("body", dataActionServer.onCheckActid2OpenidList((String)bodyMap.get("actid"), (String)bodyMap.get("openid")));
			break;
		default:
			responseMap.put("body", dataActionServer.onDefault());
			break;
		}
		responseMap.put("xraction", msgType);
		return responseMap;
	}

}
