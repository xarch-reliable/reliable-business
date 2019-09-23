package org.xarch.reliable.controller.hystrix;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.xarch.reliable.service.feign.FeignActivityinfoManager;

@Service
public class FeignActivityinfoHystrix implements FeignActivityinfoManager {

	@Override
	public Map<String, Object> setActInfo(String actInfo, Map<String, Object> actdata) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("error_msg", "[FeignActivityinfoManager]发起setActInfo请求失败");
		return map;
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
		map.put("error_msg", "[FeignActivityinfoManager]发起getActInfoByActid请求失败");
		return map;
	}

	@Override
	public Map<String, Object> setFinishActInfoByActid(String actid) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("error_msg", "[FeignActivityinfoManager]发起setFinishActInfoByActid请求失败");
		return map;
	}

	@Override
	public Map<String, Object> getActFinishByActid(String actid) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("error_msg", "[FeignActivityinfoManager]发起getActFinishByActid请求失败");
		return map;
	}

	@Override
	public Map<String, Object> addActParNumberByActid(String actid) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("error_msg", "[FeignActivityinfoManager]发起addActParNumberByActid请求失败");
		return map;
	}

	@Override
	public Map<String, Object> setActStatusByActidStatus(String openid, String actid, String status) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("error_msg", "[FeignActivityinfoManager]发起setActStatusByActidStatus请求失败");
		return map;
	}

}
