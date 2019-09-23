package org.xarch.reliable.service.feign;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.xarch.reliable.controller.hystrix.FeignOmAmHystrix;

@FeignClient(name = "reliable-actid-manager",fallback = FeignOmAmHystrix.class)
public interface FeignActidManager {
	@GetMapping("/actid/to/openid/add")
	public Map<String, Object> addAM(@RequestParam(value = "actid", required = true) String actid,
			@RequestParam(value = "openid", required = true) String openid);
	
	@GetMapping("/actid/to/openid/check")
	public Map<String, Object> checkAM(@RequestParam(value = "actid", required = true) String actid,
			@RequestParam(value = "openid", required = true) String openid);
	
	@GetMapping("/actid/to/openid/get")
	public Map<String, Object> getAM(@RequestParam(value = "actid", required = true) String actid);
}
