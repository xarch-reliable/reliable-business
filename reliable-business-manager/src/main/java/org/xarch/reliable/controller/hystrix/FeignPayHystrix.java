package org.xarch.reliable.controller.hystrix;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.xarch.reliable.service.feign.FeignPayManager;

@Service
public class FeignPayHystrix implements FeignPayManager {
	
	private static final Logger logger = LoggerFactory.getLogger(FeignPayHystrix.class);

	@Override
	public Map<String, Object> getPayMpOrder(Map<String, Object> data) {
		Map<String, Object> map = new HashMap<String, Object>();
		logger.info("[FeignPayManager]获取getPayMpOrder失败");
		map.put("error_msg", "[FeignPayManager]获取getPayMpOrder失败");
		return map;
	}

	@Override
	public Map<String, Object> getPayRefund(String payid) {
		Map<String, Object> map = new HashMap<String, Object>();
		logger.info("[FeignPayManager]获取getPayRefund失败");
		map.put("error_msg", "[FeignPayManager]获取getPayRefund失败");
		map.put("payid", payid);
		return map;
	}

}
