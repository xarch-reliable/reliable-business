package org.xarch.reliable.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.xarch.reliable.service.ActidManagerServer;

@RestController
@RequestMapping("/actid/to/openid")
public class Actid2OpenidController {

	@Autowired
	private ActidManagerServer actidManagerServer;

	@RequestMapping("/add")
	public Map<String, String> addMap(@RequestParam(value = "actid", required = true) String actid,
			@RequestParam(value = "openid", required = true) String openid) {
		return actidManagerServer.addActid2Openid(actid, openid);
	}

	@RequestMapping("/check")
	public Map<String, String> updateMap(@RequestParam(value = "actid", required = true) String actid,
			@RequestParam(value = "openid", required = true) String openid) {
		return actidManagerServer.updateActid2OpenidCheck(actid, openid);
	}
	
	@RequestMapping("/get")
	public Map<String, String> getMap(@RequestParam(value = "actid", required = true) String actid) {
		return actidManagerServer.getActid2Openid2Map(actid);
	}

}
