package org.xarch.reliable.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.xarch.reliable.sevice.BillinfoServer;
import org.xarch.reliable.util.BaseResultTools;


@RestController
@RequestMapping(value ="/bill/info")
public class RedisController {
	
	private static final Logger logger = LoggerFactory.getLogger(RedisController.class);
	@Autowired
	private BillinfoServer billinfoServer;
	@RequestMapping("/setbill")
	public Map<String, Object> setMap(@RequestParam(value = "openid", required = true) String openid,
			@RequestBody Map<String, Object> billdata) {
		
		logger.info("RedisController() :: setMap : billdata="+BaseResultTools.JsonObjectToStr(billdata));
		return billinfoServer.setBillinfo(openid, billdata);
		
	}
	
	@RequestMapping("/getbill")
	public Map<String, Object> getMap(@RequestParam(value = "openid", required = true) String openid) {
		logger.info("RedisController() :: getMap : openid="+openid);
		return billinfoServer.getBillinfo(openid);
	}
	
}
