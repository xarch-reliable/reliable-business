package org.xarch.reliable.service.bus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xarch.reliable.service.feign.FeignActInfoManager;
import org.xarch.reliable.service.feign.FeignActidManager;
import org.xarch.reliable.service.feign.FeignJsapiManager;
import org.xarch.reliable.service.feign.FeignOpenidManager;
import org.xarch.reliable.service.feign.FeignPayManager;
import org.xarch.reliable.service.feign.FeignPayidManager;
import org.xarch.reliable.service.thread.ThreadPool;

import com.google.common.collect.Lists;

@Service
public class BusinessServer extends BsinessManager {

	private static final Logger logger = LoggerFactory.getLogger(BusinessServer.class);

	@Autowired
	private FeignActInfoManager feignActInfoManager;

	@Autowired
	private FeignActidManager feignActidManager;

	@Autowired
	private FeignOpenidManager feignOpenidManager;

	@Autowired
	private FeignPayidManager feignPayidManager;

	@Autowired
	private FeignPayManager feignPayManager;
	
	@Autowired
	private FeignJsapiManager feignJsapiManager;

	@Autowired
	private ThreadPool threadPool;

	@Override
	protected Map<String, Object> onCrete(String openid, Map<String, String> data) {
		Map<String, Object> map = new HashMap<String, Object>();
		String actid = openid + String.valueOf(System.currentTimeMillis());
		Map<String, String> payIdMap = feignPayidManager.getPayid2Map(actid, openid);
		String payid = payIdMap.get(openid);
		logger.info("[payid]=" + payid);
		if (payid == null) {
			map.put("error_msg", "payID获取失败");
			return map;
		}
		data.put("actid", actid);
		data.put("creator_openid", openid);
		threadPool.StorageActInfoThread(data);
		threadPool.StorageAMThread(actid, openid);
		threadPool.StorageOMThread(openid, actid);
		Map<String, Object> paymap = feignPayManager.getPayMpOrder(openid, payid);
		map.put("actid", actid);
		map.put("paybody", paymap);
		return map;
	}

	@Override
	protected Map<String, Object> onUserInfo(Map<String, String> data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Map<String, Object> onShare(Map<String, String> data) {
		String url = data.get("url");
		return feignJsapiManager.getShareInfo(url);
	}

	@Override
	protected Map<String, Object> onActInfo(Map<String, String> data) {
		String actid = data.get("actid");
		return feignActInfoManager.getActInfoByActid(actid);
	}

	@Override
	protected List<Map<String, Object>> onAllActInfo(String openid) {
		List<Map<String, Object>> list = Lists.newArrayList();
		List<Map<String, Object>> allactinfo = feignActInfoManager.getAllActInfo();
		Map<String, String> openid2actid = feignOpenidManager.getOM(openid);
		for (Map<String, Object> actinfo :allactinfo) {
			for (String actid : openid2actid.keySet()) {
				String info2actid = (String) actinfo.get("actid");
				if(info2actid !=null && info2actid.equals(actid)) {
					list.add(actinfo);
				}
			}
		}
		return list;
	}

	@Override
	protected Map<String, Object> onJoin(String openid,Map<String, String> data) {
		Map<String, Object> resmap = new HashMap<String, Object>();
		String actid = data.get("actid");
		if(actid == null) {
			resmap.put("error_msg", "actid为空");
			return resmap;
		}
		Map<String, Object> actinfomap = feignActInfoManager.getActInfoByActid(actid);
		if(actinfomap.get("clear").equals("true")) {
			resmap.put("alert_msg", "该活动已结算，无法加入");
			return resmap;
		}else {
			Map<String, String> payIdMap = feignPayidManager.getPayid2Map(actid, openid);
			String payid = payIdMap.get(openid);
			logger.info("[payid]=" + payid);
			if (payid == null) {
				resmap.put("error_msg", "payID获取失败");
				return resmap;
			}else {
				Map<String, String> actidaddmap = feignActidManager.addAM(actid, openid);
				Map<String, String> openidaddmap = feignOpenidManager.addOM(openid, actid);
				if(actidaddmap.get("error_msg") == null && openidaddmap.get("error_msg") == null) {
					Map<String, Object> paymap = feignPayManager.getPayMpOrder(openid, payid);
					resmap.put("actid", actid);
					resmap.put("paybody", paymap);
				}else {
					resmap.put("alert_msg", "您已加入过该活动");
				}
				return resmap;
			}
		}
	}

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
		Map<String, String> openidmap = feignActidManager.getAM(actid);
		for (String item : openidmap.keySet()) {
			Map<String, String> temmap = new HashMap<String, String>();
			temmap.put("openid", item);
			list.add(temmap);
		}
		return list;
	}

	@Override
	protected Map<String, Object> onCheck(String openid, Map<String, String> data) {
		Map<String, Object> resmap = new HashMap<String, Object>();
		String actid = data.get("actid");
		if(actid == null) {
			resmap.put("error_msg", "actid为空");
			return resmap;
		}
		resmap.put("actid", actid);
		Map<String, String> actidcheckmap = feignActidManager.checkAM(actid, openid);
		Map<String, String> openidcheckmap = feignOpenidManager.checkOM(openid, actid);
		if(actidcheckmap.get("error_msg") == null && openidcheckmap.get("error_msg") == null) {
			resmap.put("alert_msg", "你很靠谱");
		}else {
			resmap.put("alert_msg", "您已经靠谱到不能再靠谱了");
		}
		return resmap;
	}

	@Override
	protected Map<String, Object> onFinish(String openid, Map<String, String> data) {
		Map<String, Object> resmap = new HashMap<String, Object>();
		String actid = data.get("actid");
		if(actid == null) {
			resmap.put("alert_msg", "actid为空，请重新加载页面");
			return resmap;
		}
		resmap.put("actid", actid);
		Map<String, String> finishactmap = feignActInfoManager.finishActInfoByActid(actid);
		if(finishactmap.get("error_msg") == null) {
			Map<String, String> actidmap = feignActidManager.getAM(actid);
			Map<String, String> payIdMap = feignPayidManager.getMap(actid);
			for (Entry<String, String> entry: actidmap.entrySet()) {
				logger.info("openid = "+entry.getKey()+" check = "+entry.getValue());
				if(entry.getValue().equals("true")) {
					String payid = payIdMap.get(entry.getKey());
					threadPool.RefundThread(payid);
				}
			}
			resmap.put("alert_msg", "结算完成");
		}else {
			resmap.put("alert_msg", "该活动已结算");
		}
		return resmap;
	}
}
