package org.xarch.reliable.service.bus;

import java.util.Map;

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
		// TODO Auto-generated method stub
		String actid = openid + String.valueOf(System.currentTimeMillis());
		Map<String, String> payIdMap = feignPayidManager.getPayid2Map(actid, openid);
		String payid = payIdMap.get(openid);
		actInfo.put("actid", actid);
		threadPool.StorageActInfoThread(actInfo);
		threadPool.StorageAMThread(actid, openid);
		threadPool.StorageOMThread(openid, actid);
		return feignPayManager.getPayMpOrder(openid, payid);
	}

	@Override
	protected Map<String, Object> onUserinfo(Map<String, String> actInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Map<String, Object> onShare() {
		// TODO Auto-generated method stub
		return null;
	}

}
