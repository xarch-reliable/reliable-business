package org.xarch.reliable.controller.hystrix;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.xarch.reliable.service.feign.FeignRefundRequestManager;
import org.xarch.reliable.service.feign.FeignRefundResponseManager;

@Service
public class FeignRefundHystrix implements FeignRefundRequestManager, FeignRefundResponseManager{

	@Override
	public Map<String, Object> setRefundResponse(String out_refund_no, Map<String, Object> data) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("error_msg", "[FeignRefundResponseManager]发起setRefundResponse请求失败");
		return map;
	}

	@Override
	public Map<String, Object> getRefundResponse(String out_refund_no) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("error_msg", "[FeignRefundResponseManager]发起setRefundResponse请求失败");
		return map;
	}

	@Override
	public Map<String, Object> setRefundRequest(String out_refund_no, Map<String, Object> data) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("error_msg", "[FeignRefundRequestManager]发起setRefundResponse请求失败");
		return map;
	}

	@Override
	public Map<String, Object> getRefundRequest(String out_refund_no) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("error_msg", "[FeignRefundRequestManager]发起setRefundResponse请求失败");
		return map;
	}

}
