package org.xarch.reliable.service.bus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xarch.reliable.service.feign.FeignClearManager;
import org.xarch.reliable.service.feign.FeignDataManager;
import org.xarch.reliable.service.feign.FeignJsapiManager;
import org.xarch.reliable.service.feign.FeignPayManager;
import org.xarch.reliable.service.thread.ThreadPool;

import com.google.common.collect.Lists;

/**
 * This is a business-specific implementation class
 * Using feign to invoke specific spring services
 * <Strong>wancy on May 24 achieving settlement processing</Strong>
 * 
 * @author wancy
 *
 */
@Service
public class BusinessServer extends BusinessManager {

	private static final Logger logger = LoggerFactory.getLogger(BusinessServer.class);

	@Autowired
	private FeignDataManager feignDataManager;

	@Autowired
	private FeignClearManager feignClearManager;
	
	@Autowired
	private FeignPayManager feignPayManager;
	
	@Autowired
	private FeignJsapiManager feignJsapiManager;

	//线程管理者
	@Autowired
	private ThreadPool threadPool;
	
    /**
    * Resolve the factory method implementing creating activity business。
	* @param String openid and Map<String, String> data
	* @return Map<String, Object> data
	* @throws Will throw an error if the data is null.
	*/
	@SuppressWarnings("unchecked")
	@Override
	protected Map<String, Object> onCrete(String openid, Map<String, String> data) {
		Map<String, Object> map = new HashMap<String, Object>();
		String actid = openid + String.valueOf(System.currentTimeMillis());
		
		Map<String, Object> sendpayidmap = new HashMap<String, Object>();
		sendpayidmap.put("xrdataction", "getpayid");
		Map<String, Object> payidmap = (Map<String, Object>)feignDataManager.doSupport2DataCenter(sendpayidmap).get("body");
		
		String payid = (String)payidmap.get("payid");
		logger.info("[payid]=" + payid);
		if (payid == null) {
			map.put("error_msg", "payID获取失败");
			return map;
		}
		data.put("actid", actid);
		data.put("creator_openid", openid);
		data.put("clear", "false");
		data.put("part_number", "0");
		data.put("status", "1");	// 活动生成  活动发布  活动签到  活动结算
		
		Map<String, Object> sendmap = new HashMap<String, Object>();
		sendmap.put("xrdataction", "setActinfoByBody");
		sendmap.put("data", data);
		threadPool.StorageActInfoThread(sendmap);
		Map<String, Object> paymap = feignPayManager.getPayMpOrder(openid, payid, actid);
		map.put("actid", actid);
		map.put("paybody", paymap);
		return map;
	}

	/**
    * Resolve the factory method implementing UserInfo business。
	* @param String openid and Map<String, String> data
	* @return Map<String, Object> data
	* @throws Will throw an error if the data is null.
	*/
	@Override
	protected Map<String, Object> onUserInfo(Map<String, String> data) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	* Resolve the factory method implementing Share business。
	* @param String openid and Map<String, String> data
	* @return Map<String, Object> data
	* @throws Will throw an error if the data is null.
	*/
	@Override
	protected Map<String, Object> onShare(Map<String, String> data) {
		String url = data.get("url");
		return feignJsapiManager.getShareInfo(url);
	}

	/**
	* Resolve the factory method implementing ActInfo business。
	* @param String openid and Map<String, String> data
	* @return Map<String, Object> data
	* @throws Will throw an error if the data is null.
	*/
	@SuppressWarnings("unchecked")
	@Override
	protected Map<String, Object> onActInfo(Map<String, String> data) {
		
		Map<String, Object> sendmap = new HashMap<String, Object>();
		Map<String, Object> datatmp = new HashMap<String, Object>();
		datatmp.put("actid", data.get("actid"));
		sendmap.put("xrdataction", "getActinfoByActid");
		sendmap.put("data", datatmp);
		Map<String, Object> resmap = (Map<String, Object>)feignDataManager.doSupport2DataCenter(sendmap).get("body");
		
		return resmap;
	}

	/**
	* Resolve the factory method implementing AllActInfo business。
	* @param String openid and Map<String, String> data
	* @return Map<String, Object> data
	* @throws Will throw an error if the data is null.
	*/
	@SuppressWarnings("unchecked")
	@Override
	protected Map<String, Object> onAllActInfo(String openid) {
		Map<String, Object> sendmap = new HashMap<String, Object>();
		Map<String, Object> datatmp = new HashMap<String, Object>();
		datatmp.put("openid", openid);
		sendmap.put("xrdataction", "getActinfoListByOpenid");
		sendmap.put("data", datatmp);
		Map<String, Object> resmap = (Map<String, Object>)feignDataManager.doSupport2DataCenter(sendmap).get("body");
		
		return resmap;
	}

	/**
	* Resolve the factory method implementing Join Activity business。
	* @param String openid and Map<String, String> data
	* @return Map<String, Object> data
	* @throws Will throw an error if the data is null.
	*/
	@SuppressWarnings("unchecked")
	@Override
	protected Map<String, Object> onJoin(String openid,Map<String, String> data) {
		Map<String, Object> resmap = new HashMap<String, Object>();
		String actid = data.get("actid");
		if(actid == null) {
			resmap.put("error_msg", "actid为空");
			return resmap;
		}
		
		Map<String, Object> sendmap = new HashMap<String, Object>();
		Map<String, Object> datatmp = new HashMap<String, Object>();
		datatmp.put("actid", actid);
		sendmap.put("xrdataction", "getactclear");
		sendmap.put("data", datatmp);
		Map<String, Object> getclearmap = (Map<String, Object>)feignDataManager.doSupport2DataCenter(sendmap).get("body");
		String clear = (String)getclearmap.get("clear");
		if(clear.equals("true")) {
			resmap.put("alert_msg", "该活动已结算，无法加入");
			return resmap;
		}else {
			
			Map<String, Object> sendpayidmap = new HashMap<String, Object>();
			sendpayidmap.put("xrdataction", "getpayid");
			Map<String, Object> payidmap = (Map<String, Object>)feignDataManager.doSupport2DataCenter(sendpayidmap).get("body");
			
			String payid = (String)payidmap.get(openid);
			logger.info("[payid]=" + payid);
			if (payid == null) {
				resmap.put("error_msg", "payID获取失败");
				return resmap;
			}else {
				
				Map<String, Object> sendmap1 = new HashMap<String, Object>();
				Map<String, Object> datatmp1 = new HashMap<String, Object>();
				datatmp1.put("openid", openid);
				datatmp1.put("actid", actid);
				sendmap1.put("xrdataction", "getOAManagerList");
				sendmap1.put("data", datatmp1);
				Map<String, Object> getOAMListmap = (Map<String, Object>)feignDataManager.doSupport2DataCenter(sendmap1).get("body");
				
				if(((Map<String, Object>)getOAMListmap.get("AMList")).get("error_msg") == null && 
						((Map<String, Object>)getOAMListmap.get("OMList")).get("error_msg") == null) {
					Map<String, Object> paymap = feignPayManager.getPayMpOrder(openid, payid, actid);
					resmap.put("actid", actid);
					resmap.put("paybody", paymap);
					
				}else {
					resmap.put("alert_msg", "您已加入过该活动");
				}
				return resmap;
			}
		}
	}

	/**
	* Resolve the factory method implementing PartUserInfo business。
	* @param String openid and Map<String, String> data
	* @return Map<String, Object> data
	* @throws Will throw an error if the data is null.
	*/
	@SuppressWarnings("unchecked")
	@Override
	protected List<Map<String, String>> onPartUserInfo(Map<String, String> data) {
		List<Map<String, String>> list = Lists.newArrayList();
		Map<String, String> map = new HashMap<String, String>();
		String actid = data.get("actid");
		if(actid == null) {
			map.put("error_msg", "actid为空");
			list.add(map);
			return list;
		}
		
		Map<String, Object> sendmap = new HashMap<String, Object>();
		Map<String, Object> datatmp = new HashMap<String, Object>();
		datatmp.put("actid", actid);
		sendmap.put("xrdataction", "getAMList");
		sendmap.put("data", datatmp);
		Map<String, Object> openidmap = (Map<String, Object>)feignDataManager.doSupport2DataCenter(sendmap).get("body");
		
		for (String item : openidmap.keySet()) {
			Map<String, String> temmap = new HashMap<String, String>();
			temmap.put("openid", item);
			list.add(temmap);
		}
		return list;
	}

	/**
	* Resolve the factory method implementing Check business。
	* @param String openid and Map<String, String> data
	* @return Map<String, Object> data
	* @throws Will throw an error if the data is null.
	*/
	@SuppressWarnings("unchecked")
	@Override
	protected Map<String, Object> onCheck(String openid, Map<String, String> data) {
		Map<String, Object> resmap = new HashMap<String, Object>();
		String actid = data.get("actid");
		if(actid == null) {
			resmap.put("error_msg", "actid为空");
			return resmap;
		}
		resmap.put("actid", actid);
		
		Map<String, Object> sendmap = new HashMap<String, Object>();
		Map<String, Object> datatmp = new HashMap<String, Object>();
		datatmp.put("openid", openid);
		datatmp.put("actid", actid);
		sendmap.put("xrdataction", "checkOAManagerList");
		sendmap.put("data", datatmp);
		Map<String, Object> getOAMListmap = (Map<String, Object>)feignDataManager.doSupport2DataCenter(sendmap).get("body");
		
		if(getOAMListmap.get("error_msg") == null) {
			resmap.put("alert_msg", "你很靠谱");
		}else {
			resmap.put("alert_msg", "您已经靠谱到不能再靠谱了");
		}
		return resmap;
	}

	/**
	* Resolve the factory method implementing Finish business。
	* @param String openid and Map<String, String> data
	* @return Map<String, Object> data
	* @throws Will throw an error if the data is null.
	*/
	@SuppressWarnings("unchecked")
	@Override
	protected Map<String, Object> onFinish(String openid, Map<String, String> data) {
		Map<String, Object> resmap = new HashMap<String, Object>();
		String actid = data.get("actid");
		if(actid == null) {
			resmap.put("alert_msg", "actid为空，请重新加载页面");
			return resmap;
		}
		resmap.put("actid", actid);
		
		
		Map<String, Object> sendmap = new HashMap<String, Object>();
		Map<String, Object> datatmp = new HashMap<String, Object>();
		datatmp.put("actid", actid);
		sendmap.put("xrdataction", "setactclear");
		sendmap.put("data", datatmp);
		Map<String, Object> finishactmap = (Map<String, Object>)feignDataManager.doSupport2DataCenter(sendmap).get("body");
		
		if(finishactmap.get("success_msg") != null) {
			
			Map<String, Object> sendmap1 = new HashMap<String, Object>();
			Map<String, Object> datatmp1 = new HashMap<String, Object>();
			datatmp1.put("actid", actid);
			sendmap1.put("xrdataction", "getAMList");
			sendmap1.put("data", datatmp1);
			Map<String, String> actidmap = (Map<String, String>)feignDataManager.doSupport2DataCenter(sendmap1).get("body");
			
			Map<String, Object> sendpayidmap = new HashMap<String, Object>();
			Map<String, Object> payidtmp = new HashMap<String, Object>();
			payidtmp.put("actid", actid);
			sendpayidmap.put("xrdataction", "getpayidMap");
			sendpayidmap.put("data", payidtmp);
			Map<String, String> payidmap = (Map<String, String>)feignDataManager.doSupport2DataCenter(sendpayidmap).get("body");
			
			for (Entry<String, String> entry: actidmap.entrySet()) {
				if(entry.getValue().equals("true")) {
					String payid = payidmap.get(entry.getKey());
					threadPool.RefundThread(payid);
				}
			}
			resmap.put("alert_msg", "结算完成");
		}else {
			resmap.put("alert_msg", "该活动已结算");
		}
		return resmap;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	protected Map<String, Object> onDraft(String openid, Map<String, String> data) {
		String draftid = String.valueOf(System.currentTimeMillis()) + openid;
		
		Map<String, Object> senddraftidmap = new HashMap<String, Object>();
		data.put("draftid", draftid);
		data.put("openid", openid);
		senddraftidmap.put("xrdataction", "setDraftinfo");
		senddraftidmap.put("data", data);
		Map<String, String> draftmap = (Map<String, String>)feignDataManager.doSupport2DataCenter(senddraftidmap).get("body");
		// TODO Auto-generated method stub
		Map<String, Object> resmap = new HashMap<String, Object>();
		if(draftmap.get("success_msg").equals("true")) {
			resmap.put("alert_msg", "已存为草稿");
		}else {
			resmap.put("alert_msg", "存入草稿失败");
		}
		
		return resmap;
	}
	
	@SuppressWarnings({ "unchecked", "null" })
	@Override
	protected Map<String, Object> onGetDraftidMap(String openid) {
		
		
		Map<String, Object> senddraftidmap = new HashMap<String, Object>();
		Map<String, Object> datatmp = new HashMap<String, Object>();
		datatmp.put("openid", openid);
		senddraftidmap.put("xrdataction", "getDraftidmap");
		senddraftidmap.put("data", datatmp);
		Map<String, Object> draftmap = (Map<String, Object>)feignDataManager.doSupport2DataCenter(senddraftidmap).get("body");
		if(draftmap!=null) {
			
			return draftmap;
		}else {
			draftmap.put("alert_msg", "获取草稿失败");
			return draftmap;
		}
		
		
	}

}
