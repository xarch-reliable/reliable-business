package org.xarch.reliable.service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.xarch.reliable.controller.hystrix.FeignPayHystrix;

@FeignClient(name = "reliable-wechat-pay-id-manager",fallback = FeignPayHystrix.class)
public interface FeignPayidManager {

	@GetMapping("/actid/to/payid/add")
	public String getPayid2Map(@RequestParam(value = "actid", required = true) String actid,
			@RequestParam(value = "openid", required = true) String openid);
	
	@GetMapping("/actid/to/payid/get")
	public String getMap(@RequestParam(value = "actid", required = true) String actid);
}