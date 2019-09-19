package org.xarch.reliable.service.feign;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.xarch.reliable.controller.hystrix.FeignOrderHystrix;

@FeignClient(name = "reliable-order-response-manager",fallback = FeignOrderHystrix.class)
public interface FeignOrderResponseManager {

	@PostMapping("/order/response/info/set")
	public Map<String, Object> setOrderResponse(@RequestParam(value = "prepay_id", required = true) String prepay_id, @RequestBody Map<String, Object> data);

	@PostMapping("/order/response/info/get")
	public Map<String, Object> getOrderResponse(@RequestParam(value = "prepay_id", required = true) String prepay_id);
	
}
