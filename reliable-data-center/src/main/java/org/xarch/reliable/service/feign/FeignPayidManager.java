package org.xarch.reliable.service.feign;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.xarch.reliable.controller.hystrix.FeignPayHystrix;

@FeignClient(name = "reliable-wechat-pay-id-manager",fallback = FeignPayHystrix.class)
public interface FeignPayidManager {

	@GetMapping("/actid/to/payid/set")
	public Map<String, Object> setPayidMap(@RequestParam(value = "actid", required = true) String actid,
			@RequestParam(value = "openid", required = true) String openid,
			@RequestParam(value = "out_trade_no", required = true) String out_trade_no);
	
	@GetMapping("/actid/to/payid/get")
	public Map<String, Object> getPayidMap(@RequestParam(value = "actid", required = true) String actid);
}
