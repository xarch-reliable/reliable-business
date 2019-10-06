package org.xarch.reliable.controller.hystrix;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	public Map<String, Object> getActCheckByActid(String actid) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("error_msg", "[FeignActivityinfoManager]发起getActCheckByActid请求失败");
		return map;
	}
	
	@Override
	public Map<String, Object> setActCheckByActid(String actid) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("error_msg", "[FeignActivityinfoManager]发起setActCheckByActid请求失败");
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

	@Override
	public Map<String, Object> getActBaoZhengHB(String actid) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("error_msg", "[FeignActivityinfoManager]发起getActBaoZhengHB请求失败");
		return map;
	}

	@Override
	public Map<String, Object> getActInfo(@RequestBody List<Object> actidlist){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("error_msg", "[FeignActivityinfoManager]发起getActInfo请求失败");
		return map;
	}
	
	@Override
	public Map<String, Object> getAllactid(){
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("error_msg", "[FeignActivityinfoManager]发起getAllactid请求失败");
		return map;
		
	}
}
