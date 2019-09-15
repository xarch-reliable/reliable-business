package org.xarch.reliable.controller.hystrix;

import java.util.HashMap;
import java.util.Map;

import org.xarch.reliable.service.feign.FeignDataManager;

public class FeignDataCenterHystrix implements FeignDataManager{

	@Override
	public Map<String, Object> doGet2DataCenter(String request) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("error_msg", "[FeignDataManager]发起数据请求失败");
		return map;
	}

}