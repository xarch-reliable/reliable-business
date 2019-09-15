package org.xarch.reliable.service.feign;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.xarch.reliable.controller.hystrix.FeignActInfoHystrix;

@FeignClient(name = "reliable-activity-info-manager",fallback = FeignActInfoHystrix.class)
public interface FeignActInfoManager {
	
	@PostMapping("/activity/info/set")
	public Map<String, String> setActInfo(@RequestBody String actInfo);
	
	@GetMapping("/activity/info/get/all")
	public List<Map<String, Object>> getAllActInfo();
	
	@GetMapping("/activity/info/get")
	public Map<String, Object> getActInfoByActid(@RequestParam(value = "actid", required = true) String actid);
	
	@GetMapping("/activity/info/finish")
	public Map<String, String> finishActInfoByActid(@RequestParam(value = "actid", required = true) String actid);
}