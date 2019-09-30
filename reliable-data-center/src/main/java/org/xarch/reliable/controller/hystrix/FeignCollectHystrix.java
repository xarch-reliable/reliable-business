package org.xarch.reliable.controller.hystrix;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.xarch.reliable.service.feign.FeignCollectinfoManager;

@Service
public class FeignCollectHystrix implements FeignCollectinfoManager {

	@Override
	public Map<String, Object> setCollectinfo(String openid, String actid) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("error_msg", "[FeignCollectinfoManager]发起setCollectinfo请求失败");
		return map;
	}

	@Override
	public Map<String, Object> getCollectinfo(String openid) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("error_msg", "[FeignCollectinfoManager]发起getCollectinfo请求失败");
		return map;
	}

}
