package org.xarch.reliable.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.xarch.reliable.service.OpenidManagerServer;

@RestController
@RequestMapping("/openid/to/actid")
public class Openid2ActidController {

	@Autowired
	private OpenidManagerServer openidManagerServer;

	@RequestMapping("/add")
	public Map<String, String> addMap(@RequestParam(value = "openid", required = true) String openid,
			@RequestParam(value = "actid", required = true) String actid) {
		return openidManagerServer.addOpenid2Actid(openid, actid);
	}

	@RequestMapping("/check")
	public Map<String, String> updateMap(@RequestParam(value = "openid", required = true) String openid,
			@RequestParam(value = "actid", required = true) String actid) {
		return openidManagerServer.updateOpenid2ActidCheck(openid, actid);
	}
	
	@RequestMapping("/get")
	public Map<String, String> getMap(@RequestParam(value = "openid", required = true) String openid) {
		return openidManagerServer.getOpenidActid2Map(openid);
	}
}
