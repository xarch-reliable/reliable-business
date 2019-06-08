package org.xarch.reliable.service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.xarch.reliable.controller.hystrix.FeignPayHystrix;

@FeignClient(name = "reliable-avtivity-info-manager",fallback = FeignPayHystrix.class)
public interface FeignActInfoManager {
	
	@PostMapping("/activity/info/set")
	public String setActid2ActInfo(@RequestBody String reliableActivityInfo);
	
	@GetMapping("/activity/info/get/all")
	public String getAllActInfo();
	
	@GetMapping("/activity/info/get")
	public String getActid2ActInfo(@RequestParam(value = "actid", required = true) String actid);
}
