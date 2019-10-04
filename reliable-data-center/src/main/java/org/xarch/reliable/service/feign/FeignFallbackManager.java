package org.xarch.reliable.service.feign;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.xarch.reliable.controller.hystrix.FeignFallbackHystrix;

@FeignClient(name = "reliable-fallback-manager",fallback = FeignFallbackHystrix.class)
public interface FeignFallbackManager {
	
	@PostMapping("/draft/info/setfallback")
	public Map<String, Object> onSetFallback(@RequestParam(value = "openid", required = true) String openid, @RequestBody Map<String, Object> data);
	
	@RequestMapping("/draft/info/getfallback")
	public Map<String, Object> onGetFallback(@RequestParam(value = "openid", required = true) String openid);
	

}
