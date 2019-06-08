package org.xarch.reliable.service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.xarch.reliable.controller.hystrix.FeignPayHystrix;

@FeignClient(name = "reliable-openid-manager",fallback = FeignPayHystrix.class)
public interface FeignOpenidManager {
	@GetMapping("/openid/to/actid/add")
	public String addOM(@RequestParam(value = "openid", required = true) String openid,
			@RequestParam(value = "actid", required = true) String actid);
	
	@GetMapping("/openid/to/actid/check")
	public String checkOM(@RequestParam(value = "openid", required = true) String openid,
			@RequestParam(value = "actid", required = true) String actid);
	
	@GetMapping("/openid/to/actid/get")
	public String getOM(@RequestParam(value = "openid", required = true) String openid);
}
