package org.xarch.reliable.service.feign;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.xarch.reliable.controller.hystrix.FeignDraftHystrix;
import org.xarch.reliable.controller.hystrix.FeignPayHystrix;

@FeignClient(name = "reliable-draft-manager",fallback = FeignDraftHystrix.class)
public interface FeignDraftManager {

	@PostMapping("/draft/info/setdraft")
	public Map<String, Object> setDraftinfo(@RequestParam(value = "draftid", required = true) String draftid, @RequestBody Map<String, Object> data);
	
	
	
	/*
	 * @PostMapping("/draft/info/setdraftid") public Map<String, Object>
	 * setDraftidinfo(@RequestParam(value = "openid", required = true) String
	 * openid, @RequestParam(value = "draftid", required = true) String draftid);
	 */
	 
}
