package org.xarch.reliable.controller.hystrix;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.xarch.reliable.service.feign.FeignOrderRequestManager;
import org.xarch.reliable.service.feign.FeignOrderResponseManager;

@Service
public class FeignOrderHystrix implements FeignOrderRequestManager, FeignOrderResponseManager{

	@Override
	public Map<String, Object> setOrderResponse(String prepay_id, Map<String, Object> data) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("error_msg", "[FeignOrderResponseManager]发起setOrderResponse请求失败");
		return map;
	}

	@Override
	public Map<String, Object> getOrderResponse(String prepay_id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("error_msg", "[FeignOrderResponseManager]发起getOrderResponse请求失败");
		return map;
	}

	@Override
	public Map<String, Object> setOrderRequest(String out_trade_no, Map<String, Object> data) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("error_msg", "[FeignOrderRequestManager]发起setOrderRequest请求失败");
		return map;
	}

	@Override
	public Map<String, Object> getOrderRequest(String out_trade_no) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("error_msg", "[FeignOrderRequestManager]发起getOrderRequest请求失败");
		return map;
	}

	@Override
	public Map<String, Object> getOrderTotalFee(String out_trade_no) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("error_msg", "[FeignOrderRequestManager]发起getOrderTotalFee请求失败");
		return map;
	}

}
