package org.xarch.reliable.controller;

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
	public String setMap(@RequestParam(value = "actid", required = true) String actid,
			@RequestBody Map<String, Object> actdata) {
		
		logger.info("RedisController() :: setMap : actdata="+BaseResultTools.JsonObjectToStr(actdata));
		return activityInfoServer.setActivityInfo(actid, actdata);
		
	}
	
	@RequestMapping("/get")
	public Map<String, Object> getMap(@RequestParam(value = "actid", required = true) String actid) {
		logger.info("RedisController() :: getMap : actid="+actid);
		return activityInfoServer.getActivityInfo(actid);
	}
	
	@RequestMapping("/clear")
	public String setClear(@RequestParam(value = "actid", required = true) String actid) {
		logger.info("RedisController() :: setClear : actid="+actid);
		return activityInfoServer.setActClear(actid);
	}
	
	@RequestMapping("/getclear")
	public String getClear(@RequestParam(value = "actid", required = true) String actid) {
		logger.info("RedisController() :: getClear : actid="+actid);
		return activityInfoServer.getActClear(actid);
	}
	
	@RequestMapping("/getselfactlist")
	public Map<String, Object> getActListByOpenid(@RequestParam(value = "openid", required = true) String openid) {
		logger.info("RedisController() :: getActListByOpenid : openid="+openid);
		return activityInfoServer.getActInfoListByOpenid(openid);
	}
	
	@RequestMapping("/addnumber")
	public String addNumber(@RequestParam(value = "actid", required = true) String actid) {
		logger.info("RedisController() :: addNumber : actid="+actid);
		return activityInfoServer.addActPartNumber(actid);
	}
	
	@RequestMapping("/setstatus")
	public String setStatus(@RequestParam(value = "actid", required = true) String actid,
			@RequestParam(value = "status", required = true) String status) {
		logger.info("RedisController() :: setStatus : actid="+actid+"  status="+status);
		return activityInfoServer.setActStatus(actid, status);
	}
	
}
