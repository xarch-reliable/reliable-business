package org.xarch.reliable.controller.hystrix;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.xarch.reliable.service.feign.FeignFallbackManager;

@Service
public class FeignFallbackHystrix implements FeignFallbackManager {

	@Override
	public Map<String, Object> onSetFallback(String openid, Map<String, Object> data) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("error_msg", "[FeignFallbackManager]发起onSetFallback请求失败");
		return map;
	}

	@Override
	public Map<String, Object> onGetFallback(String openid) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("error_msg", "[FeignFallbackManager]发起onSetFallback请求失败");
		return map;
	}

}
