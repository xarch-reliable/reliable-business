package org.xarch.reliable.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.xarch.reliable.service.ActivityInfoServer;
import org.xarch.reliable.util.BaseResultTools;

@RestController
@RequestMapping("/activity/info")
public class RedisController {
	
	private static final Logger logger = LoggerFactory.getLogger(RedisController.class);
	
	@Autowired
	private ActivityInfoServer activityInfoServer;
	
	@RequestMapping("/set")
	public Map<String, Object> setMap(@RequestParam(value = "actid", required = true) String actid,
			@RequestBody Map<String, Object> actdata) {
		return activityInfoServer.setActivityInfo(actid, actdata);
		
	}
	
	@RequestMapping("/get")
	public Map<String, Object> getMap(@RequestParam(value = "actid", required = true) String actid) {
		return activityInfoServer.getActivityInfo(actid);
	}
	
	@RequestMapping("/clear")
	public Map<String, Object> setClear(@RequestParam(value = "actid", required = true) String actid) {
		return activityInfoServer.setActClear(actid);
	}
	
	@RequestMapping("/getclear")
	public Map<String, Object> getClear(@RequestParam(value = "actid", required = true) String actid) {
		return activityInfoServer.getActClear(actid);
	}
	
	@RequestMapping("/getbaozhenghb")
	public Map<String, Object> getBaoZhengHB(@RequestParam(value = "actid", required = true) String actid) {
		return activityInfoServer.getActTotalFee(actid);
	}
	
	@RequestMapping("/getselfactlist")
	public Map<String, Object> getActListByOpenid(@RequestParam(value = "openid", required = true) String openid) {
		return activityInfoServer.getActInfoListByOpenid(openid);
	}
	///////////////////////////////////////////////////////
	@RequestMapping("/getactlist")
	public Map<String, Object> getActmapByActidlist(@RequestBody List<Object> actidlist) {
		return activityInfoServer.getActInfoListByActidlist(actidlist);
	}
	
	@RequestMapping("/addnumber")
	public Map<String, Object> addNumber(@RequestParam(value = "actid", required = true) String actid) {
		return activityInfoServer.addActPartNumber(actid);
	}
	
	@RequestMapping("/setstatus")
	public Map<String, Object> setStatus(@RequestParam(value = "openid", required = true) String openid,
			@RequestParam(value = "actid", required = true) String actid,
			@RequestParam(value = "status", required = true) String status) {
		logger.info("RedisController() :: setStatus :  openid="+openid+"  actid="+actid+"  status="+status);
		return activityInfoServer.setActStatus(openid, actid, status);
	}
	
}
