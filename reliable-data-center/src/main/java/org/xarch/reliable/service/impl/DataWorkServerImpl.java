package org.xarch.reliable.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.xarch.reliable.service.DataWorkServer;
import org.xarch.reliable.service.feign.FeignActidManager;
import org.xarch.reliable.service.feign.FeignActivityinfoManager;
import org.xarch.reliable.service.feign.FeignBillManager;
import org.xarch.reliable.service.feign.FeignCollectinfoManager;
import org.xarch.reliable.service.feign.FeignDraftManager;
import org.xarch.reliable.service.feign.FeignDraftidManager;
import org.xarch.reliable.service.feign.FeignFallbackManager;
import org.xarch.reliable.service.feign.FeignGetPayidProvider;
import org.xarch.reliable.service.feign.FeignOpenidManager;
import org.xarch.reliable.service.feign.FeignOrderNotifyManager;
import org.xarch.reliable.service.feign.FeignOrderRequestManager;
import org.xarch.reliable.service.feign.FeignOrderResponseManager;
import org.xarch.reliable.service.feign.FeignPayidManager;
import org.xarch.reliable.service.feign.FeignRefundNotifyManager;
import org.xarch.reliable.service.feign.FeignRefundRequestManager;
import org.xarch.reliable.service.feign.FeignRefundResponseManager;
import org.xarch.reliable.service.thread.ThreadPool;

@Service
public class DataWorkServerImpl implements DataWorkServer{
	
	private static final Logger logger = LoggerFactory.getLogger(DataWorkServerImpl.class);
	
	@Autowired
	private FeignActivityinfoManager feignActInfoManager;

	@Autowired
	private FeignActidManager feignActidManager;

	@Autowired
	private FeignOpenidManager feignOpenidManager;

	@Autowired
	private FeignPayidManager feignPayidManager;
	
	@Autowired
	private FeignGetPayidProvider feignGetPayidProvider;
	
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
	private FeignDraftManager feignDraftManager;
	
	@Autowired
	private FeignDraftidManager feignDraftidManager;
	
	@Autowired
	private FeignCollectinfoManager feignCollectinfoManager;

	@Autowired
	private FeignBillManager feignBillManager;
	
	@Autowired
	private FeignFallbackManager feignFallbackManager;
	
	//线程管理者
	@Autowired
	private ThreadPool threadPool;
	
	@Override
	public Map<String, Object> onGetActinfoListByOpenid(String openid) {
		return feignActInfoManager.getAllActInfo(openid);
	}

	@Override
	public Map<String, Object> onGetActinfoByActid(String actid) {
		return feignActInfoManager.getActInfoByActid(actid);
	}

	@Override
	public Map<String, Object> onGetOAManagerList(String openid, String actid) {
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap.put("AMList", feignActidManager.getAM(actid));
		resmap.put("OMList", feignOpenidManager.getOM(openid));
		return resmap;
	}

	@Override
	public Map<String, Object> onSetOAManagerList(String openid, String actid) {
		Map<String, Object> resmap = new HashMap<String, Object>();
		if(feignActidManager.addAM(actid, openid).get("error_msg") != null ||
				feignOpenidManager.addOM(openid, actid).get("error_msg") != null) {
			resmap.put("error_msg", "false");
		}else {
			resmap.put("success_msg", "true");
		}
		return resmap;
	}

	@Override
	public Map<String, Object> onGetOMList(String openid) {
		return feignOpenidManager.getOM(openid);
	}

	@Override
	public Map<String, Object> onGetAMList(String actid) {
		return feignActidManager.getAM(actid);
	}

	@Override
	public Map<String, Object> onSetActinfoByBody(Map<String, Object> data) {
		
		String actid = (String)data.get("actid");
		if(actid != null) {
			return feignActInfoManager.setActInfo(actid, data);
		}else {
			Map<String, Object> resmap = new HashMap<String, Object>();
			resmap.put("error_msg", "actid为空");
			return resmap;
		}
	}

	@Override
	public Map<String, Object> onSetActClear(String actid) {
		return feignActInfoManager.setFinishActInfoByActid(actid);
	}
	
	@Override
	public Map<String, Object> onGetActClear(String actid) {
		return feignActInfoManager.getActFinishByActid(actid);
	}
	
	@Override
	public Map<String, Object> onGetActBaoZhengHB(String actid) {
		return feignActInfoManager.getActBaoZhengHB(actid);
	}
	
	@Override
	public Map<String, Object> onGetActStatus(String actid) {
		return feignActInfoManager.getStatus(actid);
	}
	
	@Override
	public Map<String, Object> onSetActStatus(String openid, String actid, String status) {
		return feignActInfoManager.setActStatusByActidStatus(openid, actid, status);
	}

	@Override
	public Map<String, Object> onAddActPartNumber(String actid) {
		return feignActInfoManager.addActParNumberByActid(actid);
	}
	
	@Override
	public Map<String, Object> onCheckOAManagerList(String openid, String actid) {
		Map<String, Object> resmap = new HashMap<String, Object>();
		if(feignActidManager.checkAM(actid, openid).get("error_msg") != null ||
				feignOpenidManager.checkOM(openid, actid).get("error_msg") != null) {
			resmap.put("error_msg", "false");
		}else {
			resmap.put("success_msg", "true");
		}
		return resmap;
	}
	

	@Override
	public Map<String, Object> onGetOrderTotalFee(String out_trade_no) {
		return feignOrderRequestManager.getOrderTotalFee(out_trade_no);
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
			String actid = (String)data.get("attach");
			String openid = (String)data.get("openid");
			if(actid!=null && openid!=null) {
				feignActInfoManager.setActStatusByActidStatus(openid, actid, "2");
				feignActInfoManager.addActParNumberByActid(actid);
				onSetOAManagerList(openid, actid);
				onSetPayidMap(actid, openid, key);
			}
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
	public Map<String, Object> onDefault() {
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap.put("error_msg", "功能暂未开发");
		return resmap;
	}

	@Override
	public Map<String, Object> onGetPayid() {
		return feignGetPayidProvider.getPayid();
	}
	
	@Override
	public Map<String, Object> onSetPayidMap(String actid, String openid, String out_trade_no) {
		return feignPayidManager.setPayidMap(actid, openid, out_trade_no);
	}
	
	@Override
	public Map<String, Object> onGetPayidMap(String actid) {
		return feignPayidManager.getPayidMap(actid);
	}
	
	@Override
	public Map<String, Object> onSetDraftinfo(String key, Map<String, Object> data) {
	
		Map<String, Object> resmap = new HashMap<String, Object>();
		if(key != null) {
			if(feignDraftManager.setDraftinfo(key, data).get("success_msg")!=null&&feignDraftidManager.setDraftidinfo((String)data.get("openid"), key, data).get("success_msg")!=null) {
				resmap.put("success_msg", "true");
			}else {
				resmap.put("error_msg", "false");
			}
		}else {
			
			resmap.put("error_msg", "false");
			
		}
		return resmap;
	}
	
	@Override
	public Map<String, Object> onGetDraftidmap(String openid) {
		
		return feignDraftManager.getDraftMap(feignDraftidManager.getDraftidinfo(openid));
		
	}
	
	@Override
	public Map<String, Object> onSetCollectinfo(String openid, String actid) {
		
		logger.info("DataWorkServerImpl::onSetCollectinfo() : openid = " + openid+"actid=="+actid);
	
		Map<String, Object> resmap = new HashMap<String, Object>();
		if(openid != null) {
			
			if(feignCollectinfoManager.setCollectinfo(openid, actid).get("success_msg")!=null) {
				resmap.put("success_msg", "true");
			}else {
				resmap.put("error_msg", "false");
			}
			
		}else {
			logger.info("DataWorkServerImpl::onSetCollectinfo() : openid = null");
			resmap.put("error_msg", "false");
			
		}
		return resmap;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> onGetCollectmap(String openid) {
		
		List<Object> CollectList = (List<Object>)feignCollectinfoManager.getCollectinfo(openid).get("CollectList");
		return feignActInfoManager.getActInfo(CollectList);
		
	}
	
	@Override
	public Map<String, Object> onSetBillinfo(String openid, String actid, String reliableMoney) {
		
		logger.info("DataWorkServerImpl::onSetBillinfo() : openid = " + openid+"actid=="+actid+"reliableMoney"+reliableMoney);
		
		Map<String, Object> billdata = feignActInfoManager.getActInfoByActid(actid);
		
		logger.info("DataWorkServerImpl::onSetBillinfo() : billdata = " + billdata);
	
		Map<String, Object> resmap = new HashMap<String, Object>();
		if(billdata != null) {
			billdata.put("reliableMoney", reliableMoney);
			if(feignBillManager.setBillinfo(openid, billdata).get("success_msg")!=null) {
				resmap.put("success_msg", "true");
			}else {
				resmap.put("error_msg", "false");
			}
			
		}else {
			logger.info("DataWorkServerImpl::onSetBillinfo() : billdata = null");
			resmap.put("error_msg", "false");
			
		}
		return resmap;
	}
	
	@Override
	public Map<String, Object> onGetBillinfo(String openid) {
		
		return feignBillManager.getBillinfo(openid);
		
	}
	
	@Override
	public Map<String, Object> onSetFallback(String openid, Map<String, Object> data) {
	
		Map<String, Object> resmap = new HashMap<String, Object>();
		if(openid != null) {
			if(feignFallbackManager.onSetFallback(openid, data).get("success_msg")!=null) {
				resmap.put("success_msg", "true");
			}else {
				resmap.put("error_msg", "false");
			}
		}else {
			
			resmap.put("error_msg", "false");
			
		}
		return resmap;
	}
	
	@Override
	public Map<String, Object> getAllactid() {
		//Integer.parseInt((String))
		logger.info("feignActInfoManager.getAllactid()==="+feignActInfoManager.getAllactid());
		return feignActInfoManager.getAllactid();
	}

	@Override
	public Map<String, Object> onGetDistribution(String actid) {
		return feignActInfoManager.getActDistributionMethod(actid);
	}

}
