package org.xarch.reliable.controller.hystrix;

import java.util.HashMap;
import java.util.Map;

import org.xarch.reliable.service.feign.FeignBillManager;

public class FeignBillHystrix implements FeignBillManager {

	@Override
	public Map<String, Object> setBillinfo(String openid, Map<String, Object> billdata) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("error_msg", "[FeignBillManager]发起setBillinfo请求失败");
		return map;
	}

	@Override
	public Map<String, Object> getBillinfo(String openid) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("error_msg", "[FeignBillManager]发起getBillinfo请求失败");
		return map;
	}

}
