package org.xarch.reliable.service.feign;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.xarch.reliable.controller.hystrix.FeignOmAmHystrix;

@FeignClient(name = "reliable-openid-manager",fallback = FeignOmAmHystrix.class)
public interface FeignOpenidManager {
	@GetMapping("/openid/to/actid/add")
	public Map<String, Object> addOM(@RequestParam(value = "openid", required = true) String openid,
			@RequestParam(value = "actid", required = true) String actid);
	
	@GetMapping("/openid/to/actid/check")
	public Map<String, Object> checkOM(@RequestParam(value = "openid", required = true) String openid,
			@RequestParam(value = "actid", required = true) String actid);
	
	@GetMapping("/openid/to/actid/get")
	public Map<String, Object> getOM(@RequestParam(value = "openid", required = true) String openid);
}
