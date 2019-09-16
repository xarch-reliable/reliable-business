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
	public boolean setMap(@RequestParam(value = "actid", required = true) String actid,
			@RequestBody Map<String, Object> actdata) {
		return activityInfoServer.setActivityInfo(actid, actdata);
		
	}
	
	@RequestMapping("/get")
	public Map<String, Object> getMap(@RequestParam(value = "actid", required = true) String actid) {

		return activityInfoServer.getActivityInfo(actid);
	}
	
	@RequestMapping("/clear")
	public boolean setClear(@RequestParam(value = "actid", required = true) String actid) {

		return activityInfoServer.setActClear(actid);
	}
	
	@RequestMapping("/getselfactlist")
	public Map<String, Object> getActListByOpenid(@RequestParam(value = "openid", required = true) String openid) {

		return activityInfoServer.getActInfoListByOpenid(openid);
	}
	
}
