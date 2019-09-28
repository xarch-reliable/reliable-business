package org.xarch.reliable.controller;

import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.xarch.reliable.service.DraftidinfoServer;
import org.xarch.reliable.util.BaseResultTools;

@RestController
@RequestMapping(value ="/draft/info")
public class RedisController {
	
	private static final Logger logger = LoggerFactory.getLogger(RedisController.class);
	@Autowired
	private DraftidinfoServer draftidinfoServer;
	@RequestMapping("/setdraftid")
	public Map<String, Object> setMap(@RequestParam(value = "openid", required = true) String openid,
			@RequestParam(value = "draftid", required = true) String draftid,@RequestBody Map<String, Object> draftdata) {
		
		logger.info("RedisController() :: setdraftid : openid="+openid+"draftid="+ draftid);
		return draftidinfoServer.setDraftidinfo(openid, draftid,draftdata);
		
	}
	@RequestMapping("/getdraftid")
	public Set getMap(@RequestParam(value = "openid", required = true) String openid) {
		logger.info("RedisController() :: getdraftid : openid="+openid);
		return draftidinfoServer.getDraftidinfo(openid);
	}
	

}
