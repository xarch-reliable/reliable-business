package org.xarch.reliable.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.xarch.reliable.sevice.DraftinfoServer;
import org.xarch.reliable.util.BaseResultTools;


@RestController
@RequestMapping(value ="/draft/info")
public class RedisController {
	
	private static final Logger logger = LoggerFactory.getLogger(RedisController.class);
	@Autowired
	private DraftinfoServer draftinfoServer;
	@RequestMapping("/setdraft")
	public Map<String, Object> setMap(@RequestParam(value = "draftid", required = true) String draftid,
			@RequestBody Map<String, Object> draftdata) {
		
		logger.info("RedisController() :: setMap : draftdata="+BaseResultTools.JsonObjectToStr(draftdata));
		return draftinfoServer.setDraftinfo(draftid, draftdata);
		
	}
	@RequestMapping("/getdraft")
	public Map<String, Object> getMap(@RequestParam(value = "openid", required = true) String openid) {
		logger.info("RedisController() :: getMap : openid="+openid);
		return draftinfoServer.getDraftinfo(openid);
	}
	
}
