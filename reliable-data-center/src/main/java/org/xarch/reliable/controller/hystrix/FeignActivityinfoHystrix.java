package org.xarch.reliable.controller.hystrix;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.xarch.reliable.service.feign.FeignActivityinfoManager;

@Service
public class FeignActivityinfoHystrix implements FeignActivityinfoManager {

	@Override
	public String setActInfo(String actInfo, Map<String, Object> actdata) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("error_msg", "[FeignActivityinfoManager]发起setActid2ActInfo请求失败");
		return "false";
	}

	@Override
	public Map<String, Object> getAllActInfo(String openid) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("error_msg", "[FeignActivityinfoManager]发起getAllActInfo请求失败");
		return map;
	}

	@Override
	public Map<String, Object> getActInfoByActid(String actid) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("error_msg", "[FeignActivityinfoManager]发起getActid2ActInfo请求失败");
		map.put("actid", actid);
		return map;
	}

	@Override
	public String finishActInfoByActid(String actid) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("error_msg", "[FeignActivityinfoManager]发起clear请求失败");
		return "false";
	}

	@Override
	public String getActfinishByActid(String actid) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("error_msg", "[FeignActivityinfoManager]发起clear请求失败");
		return "false";
	}

}
