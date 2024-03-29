package org.xarch.reliable.service.thread;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.xarch.reliable.service.feign.FeignClearManager;
import org.xarch.reliable.service.feign.FeignDataManager;
import org.xarch.reliable.service.feign.FeignJsapiManager;
import org.xarch.reliable.service.feign.FeignPayManager;

@Component
public class ThreadPool {

	@Autowired
	private FeignDataManager feignDataManager;
	
	@Autowired
	private FeignClearManager feignClearManager;
	
	@Autowired
	private FeignJsapiManager feignJsapiManager;

	@Autowired
	private FeignPayManager feignPayManager;

	@Async("asyncExecutor")
	public void StorageActInfoThread(Map<String, Object> sendata) {
		feignDataManager.doSupport2DataCenter(sendata);
	}
	
	@Async("asyncExecutor")
	public void RefundThread(String payid) {
		feignPayManager.getPayRefund(payid);
	}

	@Async("asyncExecutor")
	public void ClearThread(Map<String, Object> sendata) {
		feignClearManager.doSupport2ClearCenter(sendata);
	}
	
	@Async("asyncExecutor")
	public void CreateShareQrCode(String actid) {
		feignJsapiManager.pushJoinQrCode(actid);
	}
	
	@Async("asyncExecutor")
	public void CreateCheckQrCode(String actid) {
		feignJsapiManager.pushCheckQrCode(actid);

	}
}
