package org.xarch.reliable.controller.hystrix;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.xarch.reliable.service.feign.FeignDraftManager;

@Service
public class FeignDraftHystrix implements FeignDraftManager {

	@Override
	public Map<String, Object> setDraftinfo(String draftid, Map<String, Object> data) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("error_msg", "[FeignDraftManager]发起setDraftinfo请求失败");
		return map;
	}

	/*
	 * @Override public Map<String, Object> setDraftidinfo(String openid, String
	 * draftid) { // TODO Auto-generated method stub Map<String, Object> map = new
	 * HashMap<String, Object>(); map.put("error_msg",
	 * "[FeignDraftidManager]发起setDraftidinfo请求失败"); return map; }
	 */

}
