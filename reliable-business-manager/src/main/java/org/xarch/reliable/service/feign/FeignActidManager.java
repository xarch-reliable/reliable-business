package org.xarch.reliable.service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.xarch.reliable.controller.hystrix.FeignPayHystrix;

@FeignClient(name = "reliable-actid-manager",fallback = FeignPayHystrix.class)
public interface FeignActidManager {
	@GetMapping("/actid/to/openid/add")
	public String addAM(@RequestParam(value = "actid", required = true) String actid,
			@RequestParam(value = "openid", required = true) String openid);
	
	@GetMapping("/actid/to/openid/check")
	public String checkAM(@RequestParam(value = "actid", required = true) String actid,
			@RequestParam(value = "openid", required = true) String openid);
	
	@GetMapping("/actid/to/openid/get")
	public String getAM(@RequestParam(value = "actid", required = true) String actid);
}
