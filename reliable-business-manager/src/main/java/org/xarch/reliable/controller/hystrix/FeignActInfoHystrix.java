package org.xarch.reliable.controller.hystrix;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.xarch.reliable.service.feign.FeignActInfoManager;

import com.google.common.collect.Lists;

@Service
public class FeignActInfoHystrix implements FeignActInfoManager {

	@Override
	public Map<String, String> setActInfo(String actInfo) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("error_msg", "[FeignActInfoManager]发起setActid2ActInfo请求失败");
		return map;
	}

	@Override
	public List<Map<String, Object>> getAllActInfo() {
		List<Map<String, Object>> list = Lists.newArrayList();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("error_msg", "[FeignActInfoManager]发起getAllActInfo请求失败");
		list.add(map);
		return list;
	}

	@Override
	public Map<String, Object> getActInfoByActid(String actid) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("error_msg", "[FeignActInfoManager]发起getActid2ActInfo请求失败");
		map.put("actid", actid);
		return map;
	}

}
