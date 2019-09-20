package org.xarch.reliable.controller.hystrix;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.xarch.reliable.service.feign.FeignPayManager;
import org.xarch.reliable.service.feign.FeignPayidManager;

@Service
public class FeignPayHystrix implements FeignPayManager, FeignPayidManager {
	
	private static final Logger logger = LoggerFactory.getLogger(FeignPayHystrix.class);

	@Override
	public Map<String, String> getPayid2Map(String actid, String openid) {
		Map<String, String> map = new HashMap<String, String>();
		logger.info("[FeignPayidManager]获取getPayid2Map失败");
		map.put("error_msg", "[FeignPayidManager]获取getPayid2Map失败");
		map.put("openid", openid);
		map.put("actid", actid);
		return map;
	}

	@Override
	public Map<String, String> getMap(String actid) {
		Map<String, String> map = new HashMap<String, String>();
		logger.info("[FeignPayidManager]获取getMap失败");
		map.put("error_msg", "[FeignPayidManager]获取getMap失败");
		map.put("actid", actid);
		return map;
	}

	@Override
	public Map<String, Object> getPayMpOrder(String openid, String payid, String actid) {
		Map<String, Object> map = new HashMap<String, Object>();
		logger.info("[FeignPayManager]获取getPayMpOrder失败");
		map.put("error_msg", "[FeignPayManager]获取getPayMpOrder失败");
		map.put("openid", openid);
		map.put("payid", payid);
		map.put("actid", actid);
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
