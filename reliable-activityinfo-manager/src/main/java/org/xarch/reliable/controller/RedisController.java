package org.xarch.reliable.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.xarch.reliable.service.ActivityInfoServer;

@RestController
@RequestMapping("/activity/info")
public class RedisController {
	
	@Autowired
	private ActivityInfoServer activityInfoServer;
	
	@RequestMapping("/set")
	public String setMap(@RequestParam(value = "actid", required = true) String actid,
			@RequestBody Map<String, Object> actdata) {
		return activityInfoServer.setActivityInfo(actid, actdata);
		
	}
	
	@RequestMapping("/get")
	public Map<String, Object> getMap(@RequestParam(value = "actid", required = true) String actid) {

		return activityInfoServer.getActivityInfo(actid);
	}
	
	@RequestMapping("/clear")
	public String setClear(@RequestParam(value = "actid", required = true) String actid) {

		return activityInfoServer.setActClear(actid);
	}
	
	@RequestMapping("/getclear")
	public String getClear(@RequestParam(value = "actid", required = true) String actid) {

		return activityInfoServer.getActClear(actid);
	}
	
	@RequestMapping("/getselfactlist")
	public Map<String, Object> getActListByOpenid(@RequestParam(value = "openid", required = true) String openid) {

		return activityInfoServer.getActInfoListByOpenid(openid);
	}
	
	@RequestMapping("/addnumber")
	public String addNumber(@RequestParam(value = "actid", required = true) String actid) {

		return activityInfoServer.getActClear(actid);
	}
	
	@RequestMapping("/setstatus")
	public String setStatus(@RequestParam(value = "actid", required = true) String actid,
			@RequestParam(value = "status", required = true) String status) {

		return activityInfoServer.getActClear(actid);
	}
	
}
