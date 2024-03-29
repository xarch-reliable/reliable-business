package org.xarch.reliable.service.thread;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.xarch.reliable.service.feign.FeignActidManager;
import org.xarch.reliable.service.feign.FeignActivityinfoManager;
import org.xarch.reliable.service.feign.FeignOpenidManager;
import org.xarch.reliable.service.feign.FeignPayManager;
import org.xarch.reliable.utils.BaseResultTools;

@Component
public class ThreadPool {

	@Autowired
	private FeignActivityinfoManager feignActInfoManager;

	@Autowired
	private FeignActidManager feignActidManager;

	@Autowired
	private FeignOpenidManager feignOpenidManager;

	@Autowired
	private FeignPayManager feignPayManager;

	@Async("asyncExecutor")
	public void StorageActInfoThread(String actid, Map<String, Object> actInfo) {
		feignActInfoManager.setActInfo(actid, actInfo);
	}

	@Async("asyncExecutor")
	public void StorageAMThread(String actid, String openid) {
		feignActidManager.addAM(actid, openid);
	}

	@Async("asyncExecutor")
	public void StorageOMThread(String openid, String actid) {
		feignOpenidManager.addOM(openid, actid);
	}
	
	@Async("asyncExecutor")
	public void RefundThread(String payid) {
		feignPayManager.getPayRefund(payid);
	}

}
