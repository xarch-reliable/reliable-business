package org.xarch.reliable.service.feign;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.xarch.reliable.controller.hystrix.FeignCollectHystrix;
import org.xarch.reliable.controller.hystrix.FeignDraftidHystrix;

@FeignClient(name = "reliable-collect-manager",fallback = FeignCollectHystrix.class)
public interface FeignCollectinfoManager {
	
	@PostMapping("/collect/info/setcollect") 
	public Map<String, Object> setCollectinfo(@RequestParam(value = "openid", required = true) String openid,
			@RequestParam(value = "actid", required = true) String actid);
	
	@PostMapping("/collect/info/getcollect") 
	public Map<String, Object> getCollectinfo(@RequestParam(value = "openid", required = true) String openid);

}
