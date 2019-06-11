package org.xarch.reliable.service.bus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xarch.reliable.service.feign.FeignActInfoManager;
import org.xarch.reliable.service.feign.FeignActidManager;
import org.xarch.reliable.service.feign.FeignOpenidManager;
import org.xarch.reliable.service.feign.FeignPayManager;
import org.xarch.reliable.service.feign.FeignPayidManager;
import org.xarch.reliable.service.thread.ThreadPool;

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
	private ThreadPool threadPool;

	@Override
	protected Map<String, Object> onCrete(String openid, Map<String, String> actInfo) {
		Map<String, Object> map = new HashMap<String, Object>();
		String actid = openid + String.valueOf(System.currentTimeMillis());
		Map<String, String> payIdMap = feignPayidManager.getPayid2Map(actid, openid);
		String payid = payIdMap.get(openid);
		logger.info("[payid]=" + payid);
		if (payid == null) {
			map.put("error_msg", "payID获取失败");
			return map;
		}
		actInfo.put("actid", actid);
		threadPool.StorageActInfoThread(actInfo);
		threadPool.StorageAMThread(actid, openid);
		threadPool.StorageOMThread(openid, actid);
		Map<String, Object> paymap = feignPayManager.getPayMpOrder(openid, payid);
		map.put("actid", "actid");
		map.put("paybody", paymap);
		return map;
	}

	@Override
	protected Map<String, Object> onUserInfo(Map<String, String> actInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Map<String, Object> onShare() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Map<String, Object> onActInfo(String actid) {
		return feignActInfoManager.getActid2ActInfo(actid);
	}

	@Override
	protected List<Map<String,Object>> onAllActInfo() {
		return feignActInfoManager.getAllActInfo();
	}

}
