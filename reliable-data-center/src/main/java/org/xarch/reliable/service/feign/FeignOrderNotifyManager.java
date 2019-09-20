package org.xarch.reliable.service.feign;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.xarch.reliable.controller.hystrix.FeignNotifyHystrix;

@FeignClient(name = "reliable-order-notify-manager",fallback = FeignNotifyHystrix.class)
public interface FeignOrderNotifyManager {

	@PostMapping("/order/notify/info/set")
	public Map<String, Object> setOrderNotify(@RequestParam(value = "out_trade_no", required = true) String out_trade_no, @RequestBody Map<String, Object> data);

	@PostMapping("/order/notify/info/get")
	public Map<String, Object> getOrderNotify(@RequestParam(value = "out_trade_no", required = true) String out_trade_no);
	
}
