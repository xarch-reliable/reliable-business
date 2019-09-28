package org.xarch.reliable.service.feign;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.xarch.reliable.controller.hystrix.FeignOrderHystrix;

@FeignClient(name = "reliable-order-request-manager",fallback = FeignOrderHystrix.class)
public interface FeignOrderRequestManager {
	
	@PostMapping("/order/request/info/set")
	public Map<String, Object> setOrderRequest(@RequestParam(value = "out_trade_no", required = true) String out_trade_no, @RequestBody Map<String, Object> data);

	@PostMapping("/order/request/info/get")
	public Map<String, Object> getOrderRequest(@RequestParam(value = "out_trade_no", required = true) String out_trade_no);
	
	@PostMapping("/order/request/get/totalfee")
	public Map<String, Object> getOrderTotalFee(@RequestParam(value = "out_trade_no", required = true) String out_trade_no);
}
