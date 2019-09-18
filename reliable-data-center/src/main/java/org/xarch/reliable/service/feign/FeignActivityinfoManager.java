package org.xarch.reliable.service.feign;

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
	public String setActInfo(@RequestParam(value = "actid", required = true) String actid, @RequestBody Map<String, Object> actdata);
	
	@GetMapping("/activity/info/getselfactlist")
	public Map<String, Object> getAllActInfo(@RequestParam(value = "openid", required = true) String openid);
	
	@GetMapping("/activity/info/get")
	public Map<String, Object> getActInfoByActid(@RequestParam(value = "actid", required = true) String actid);
	
	@GetMapping("/activity/info/clear")
	public String setFinishActInfoByActid(@RequestParam(value = "actid", required = true) String actid);
	
	@GetMapping("/activity/info/getclear")
	public String getActFinishByActid(@RequestParam(value = "actid", required = true) String actid);
	
	@GetMapping("/activity/info/addnumber")
	public String addActParNumberByActid(@RequestParam(value = "actid", required = true) String actid);
	
	@GetMapping("/activity/info/setstatus")
	public String setActStatusByActidStatus(@RequestParam(value = "actid", required = true) String actid, @RequestParam(value = "status", required = true) String status);
}
