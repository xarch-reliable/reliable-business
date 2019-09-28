package org.xarch.reliable.service.feign;

import java.util.Map;
import java.util.Set;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.xarch.reliable.controller.hystrix.FeignDraftHystrix;
import org.xarch.reliable.controller.hystrix.FeignDraftidHystrix;

@FeignClient(name = "reliable-draftid-manager",fallback = FeignDraftidHystrix.class)
public interface FeignDraftidManager {

	@PostMapping("/draft/info/setdraftid") 
	public Map<String, Object> setDraftidinfo(@RequestParam(value = "openid", required = true) String openid, @RequestParam(value = "draftid", required = true) String draftid,@RequestBody Map<String, Object> draftdata);

	@PostMapping("/draft/info/getdraftid") 
	public Set getDraftidinfo(@RequestParam(value = "openid", required = true) String openid);
}
