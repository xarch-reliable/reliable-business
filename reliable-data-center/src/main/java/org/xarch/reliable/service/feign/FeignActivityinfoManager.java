package org.xarch.reliable.service.feign;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.xarch.reliable.controller.hystrix.FeignActInfoHystrix;

@FeignClient(name = "reliable-activityinfo-manager",fallback = FeignActInfoHystrix.class)
public interface FeignActivityinfoManager {
	
	@PostMapping("/activity/info/set")
	public String setActInfo(@RequestParam(value = "actid", required = true) String actid, @RequestBody Map<String, Object> actdata);
	
	@GetMapping("/activity/info/get/getselfactlist")
	public Map<String, Object> getAllActInfo(@RequestParam(value = "actid", required = true) String openid);
	
	@GetMapping("/activity/info/get")
	public Map<String, Object> getActInfoByActid(@RequestParam(value = "actid", required = true) String actid);
	
	@GetMapping("/activity/info/clear")
	public String finishActInfoByActid(@RequestParam(value = "actid", required = true) String actid);
	
	@GetMapping("/activity/info/getclear")
	public String getActfinishByActid(@RequestParam(value = "actid", required = true) String actid);
}
