package org.xarch.reliable.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.xarch.reliable.service.DraftidinfoServer;


@RestController
@RequestMapping(value ="/collect/info")
public class RedisController {
	
	private static final Logger logger = LoggerFactory.getLogger(RedisController.class);
	@Autowired
	private DraftidinfoServer draftidinfoServer;
	@RequestMapping("/setcollect")
	public Map<String, Object> setMap(@RequestParam(value = "openid", required = true) String openid,
			@RequestParam(value = "actid", required = true) String actid) {
		
		logger.info("RedisController() :: setdraftid : openid="+openid+"actid="+ actid);
		return draftidinfoServer.setCollectinfo(openid, actid);
		
	}
	@RequestMapping("/getcollect")
	public Map<String, Object> getMap(@RequestParam(value = "openid", required = true) String openid) {
		logger.info("RedisController() :: getdraftid : openid="+openid);
		return draftidinfoServer.getCollectinfo(openid);
	}

}
