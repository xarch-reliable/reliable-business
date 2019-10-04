package org.xarch.reliable.service.feign;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.xarch.reliable.controller.hystrix.FeignActivityinfoHystrix;

@FeignClient(name = "reliable-activityinfo-manager",fallback = FeignActivityinfoHystrix.class)
public interface FeignActivityinfoManager {
	
	@PostMapping("/activity/info/set")
	public Map<String, Object> setActInfo(@RequestParam(value = "actid", required = true) String actid, @RequestBody Map<String, Object> actdata);
	
	@GetMapping("/activity/info/getselfactlist")
	public Map<String, Object> getAllActInfo(@RequestParam(value = "openid", required = true) String openid);
	
	/////////////////////////////////////////////////////////////////////
	@GetMapping("/activity/info/getactlist")
	public Map<String, Object> getActInfo(@RequestBody List<Object> actidlist);
	
	@GetMapping("/activity/info/get")
	public Map<String, Object> getActInfoByActid(@RequestParam(value = "actid", required = true) String actid);
	
	@GetMapping("/activity/info/clear")
	public Map<String, Object> setFinishActInfoByActid(@RequestParam(value = "actid", required = true) String actid);
	
	@GetMapping("/activity/info/getclear")
	public Map<String, Object> getActFinishByActid(@RequestParam(value = "actid", required = true) String actid);
	
	@GetMapping("/activity/info/getbaozhenghb")
	public Map<String, Object> getActBaoZhengHB(@RequestParam(value = "actid", required = true) String actid);
	
	@GetMapping("/activity/info/addnumber")
	public Map<String, Object> addActParNumberByActid(@RequestParam(value = "actid", required = true) String actid);
	
	@GetMapping("/activity/info/setstatus")
	public Map<String, Object> setActStatusByActidStatus(@RequestParam(value = "openid", required = true) String openid, @RequestParam(value = "actid", required = true) String actid, @RequestParam(value = "status", required = true) String status);
	
	@GetMapping("/activity/info/getlist")
	public Map<String, Object> getAllactid();
}
