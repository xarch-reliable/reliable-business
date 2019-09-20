package org.xarch.reliable.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xarch.reliable.service.DataActionServer;
import org.xarch.reliable.service.feign.FeignActidManager;
import org.xarch.reliable.service.feign.FeignActivityinfoManager;
import org.xarch.reliable.service.feign.FeignJsapiManager;
import org.xarch.reliable.service.feign.FeignOpenidManager;
import org.xarch.reliable.service.feign.FeignOrderNotifyManager;
import org.xarch.reliable.service.feign.FeignOrderRequestManager;
import org.xarch.reliable.service.feign.FeignOrderResponseManager;
import org.xarch.reliable.service.feign.FeignPayManager;
import org.xarch.reliable.service.feign.FeignPayidManager;
import org.xarch.reliable.service.feign.FeignRefundNotifyManager;
import org.xarch.reliable.service.feign.FeignRefundRequestManager;
import org.xarch.reliable.service.feign.FeignRefundResponseManager;
import org.xarch.reliable.service.thread.ThreadPool;

@Service
public class DataActionServerImpl implements DataActionServer{
	
	private static final Logger logger = LoggerFactory.getLogger(DataActionServerImpl.class);

	@Autowired
	private FeignActivityinfoManager feignActInfoManager;

	@Autowired
	private FeignActidManager feignActidManager;

	@Autowired
	private FeignOpenidManager feignOpenidManager;

	@Autowired
	private FeignPayidManager feignPayidManager;

	@Autowired
	private FeignPayManager feignPayManager;
	
	@Autowired
	private FeignOrderRequestManager feignOrderRequestManager;

	@Autowired
	private FeignOrderResponseManager feignOrderResponseManager;
	
	@Autowired
	private FeignRefundRequestManager feignRefundRequestManager;
	
	@Autowired
	private FeignRefundResponseManager feignRefundResponseManager;
	
	@Autowired
	private FeignOrderNotifyManager feignOrderNotifyManager;
	
	@Autowired
	private FeignRefundNotifyManager feignRefundNotifyManager;
	
	@Autowired
	private FeignJsapiManager feignJsapiManager;

	//线程管理者
	@Autowired
	private ThreadPool threadPool;
	
	@Override
	public Map<String, Object> onGetActinfoListByOpenid(String openid) {
		return feignActInfoManager.getAllActInfo(openid);
	}

	@Override
	public Map<String, String> onGetActidListByOpenid(String openid) {
		return feignOpenidManager.getOM(openid);
	}

	@Override
	public Map<String, String> onGetOpenidListByActid(String actid) {
		return feignActidManager.getAM(actid);
	}

	@Override
	public Map<String, Object> onGetActinfoByActid(String actid) {
		return feignActInfoManager.getActInfoByActid(actid);
	}

	@Override
	public Map<String, Object> onSetOpenid2ActidList(String openid, String actid) {
		return null;
	}

	@Override
	public Map<String, Object> onSetActid2OpenidList(String actid, String openid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String onSetActinfoByBody(Map<String, Object> data) {
		String actid = (String)data.get("actid");
		if(actid != null) {
			return feignActInfoManager.setActInfo(actid, data);
		}else {
			return "false";
		}
	}

	@Override
	public String onSetActClear(String actid) {
		return feignActInfoManager.setFinishActInfoByActid(actid);
	}
	
	@Override
	public String onGetActClear(String actid) {
		return feignActInfoManager.getActFinishByActid(actid);
	}
	
	@Override
	public String onSetActStatus(String actid, String status) {
		return feignActInfoManager.setActStatusByActidStatus(actid, status);
	}

	@Override
	public String onAddActPartNumber(String actid) {
		return feignActInfoManager.addActParNumberByActid(actid);
	}
	
	@Override
	public Map<String, Object> onCheckOpenid2ActidList(String openid, String actid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> onCheckActid2OpenidList(String actid, String openid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> onSetOrderRequest(String key, Map<String, Object> data) {
		if(key != null) {
			return feignOrderRequestManager.setOrderRequest(key, data);
		}else {
			data.put("error_msg", "false");
			return data;
		}
	}

	@Override
	public Map<String, Object> onGetOrderRequest(String key) {
		if(key != null) {
			return feignOrderRequestManager.getOrderRequest(key);
		}else {
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("error_msg", "false");
			return data;
		}
	}

	@Override
	public Map<String, Object> onSetOrderResponse(String key, Map<String, Object> data) {
		if(key != null) {
			return feignOrderResponseManager.setOrderResponse(key, data);
		}else {
			data.put("error_msg", "false");
			return data;
		}
	}

	@Override
	public Map<String, Object> onGetOrderResponse(String key) {
		if(key != null) {
			return feignOrderResponseManager.getOrderResponse(key);
		}else {
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("error_msg", "false");
			return data;
		}
	}

	@Override
	public Map<String, Object> onSetRefundRequest(String key, Map<String, Object> data) {
		if(key != null) {
			return feignRefundRequestManager.setRefundRequest(key, data);
		}else {
			data.put("error_msg", "false");
			return data;
		}
	}

	@Override
	public Map<String, Object> onGetRefundRequest(String key) {
		if(key != null) {
			return feignRefundRequestManager.getRefundRequest(key);
		}else {
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("error_msg", "false");
			return data;
		}
	}

	@Override
	public Map<String, Object> onSetRefundResponse(String key, Map<String, Object> data) {
		if(key != null) {
			return feignRefundResponseManager.setRefundResponse(key, data);
		}else {
			data.put("error_msg", "false");
			return data;
		}
	}
	
	@Override
	public Map<String, Object> onGetRefundResponse(String key) {
		if(key != null) {
			return feignRefundResponseManager.getRefundResponse(key);
		}else {
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("error_msg", "false");
			return data;
		}
	}

	@Override
	public Map<String, Object> onSetOrderNotify(String key, Map<String, Object> data) {
		if(key != null) {
			return feignOrderNotifyManager.setOrderNotify(key, data);
		}else {
			data.put("error_msg", "false");
			return data;
		}
	}

	@Override
	public Map<String, Object> onGetOrderNotify(String key) {
		if(key != null) {
			return feignOrderNotifyManager.getOrderNotify(key);
		}else {
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("error_msg", "false");
			return data;
		}
	}

	@Override
	public Map<String, Object> onSetRefundNotify(String key, Map<String, Object> data) {
		if(key != null) {
			return feignRefundNotifyManager.setRefundNotify(key, data);
		}else {
			data.put("error_msg", "false");
			return data;
		}
	}

	@Override
	public Map<String, Object> onGetRefundNotify(String key) {
		if(key != null) {
			return feignRefundNotifyManager.getRefundNotify(key);
		}else {
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("error_msg", "false");
			return data;
		}
	}
	
	@Override
	public String onDefault() {
		// TODO Auto-generated method stub
		return null;
	}
}
