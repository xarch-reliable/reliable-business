package org.xarch.reliable.controller.hystrix;

import java.util.HashMap;
import java.util.Map;

import org.xarch.reliable.service.feign.FeignOrderNotifyManager;
import org.xarch.reliable.service.feign.FeignRefundNotifyManager;

public class FeignNotifyHystrix implements FeignOrderNotifyManager, FeignRefundNotifyManager{

	@Override
	public Map<String, Object> setOrderNotify(String out_trade_no, Map<String, Object> data) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("error_msg", "[FeignOrderNotifyManager]发起setOrderRequest请求失败");
		return map;
	}

	@Override
	public Map<String, Object> getOrderNotify(String out_trade_no) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("error_msg", "[FeignOrderNotifyManager]发起getOrderRequest请求失败");
		return map;
	}

	@Override
	public Map<String, Object> setRefundNotify(String out_trade_no, Map<String, Object> data) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("error_msg", "[FeignRefundNotifyManager]发起setRefundRequest请求失败");
		return map;
	}

	@Override
	public Map<String, Object> getRefundNotify(String out_trade_no) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("error_msg", "[FeignRefundNotifyManager]发起getRefundRequest请求失败");
		return map;
	}

}
