package org.xarch.reliable.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.xarch.reliable.service.OpenidManagerServer;

@RestController
@RequestMapping("/openid/to/actid")
public class Openid2ActidController {
	
	private static final Logger logger = LoggerFactory.getLogger(Openid2ActidController.class);

	@Autowired
	private OpenidManagerServer openidManagerServer;

	@RequestMapping("/add")
	public Map<String, String> addMap(@RequestParam(value = "openid", required = true) String openid,
			@RequestParam(value = "actid", required = true) String actid) {
		logger.info("[openid]"+openid);
		return openidManagerServer.addOpenid2Actid(openid, actid);
	}

	@RequestMapping("/check")
	public Map<String, String> updateMap(@RequestParam(value = "openid", required = true) String openid,
			@RequestParam(value = "actid", required = true) String actid) {
		logger.info("[openid]"+openid);
		return openidManagerServer.updateOpenid2ActidCheck(openid, actid);
	}
	
	@RequestMapping("/get")
	public Map<String, String> getMap(@RequestParam(value = "openid", required = true) String openid) {
		logger.info("[openid]"+openid);
		return openidManagerServer.getOpenidActid2Map(openid);
	}
}
