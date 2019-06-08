package org.xarch.reliable.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.xarch.reliable.model.domain.reliable.ReliableActivityInfo;
import org.xarch.reliable.service.ReliableActInfoServer;

@RestController
@RequestMapping("/activity/info")
public class ActInfoController {

	@Autowired
	private ReliableActInfoServer reliableActInfoServer;

	@PostMapping("/set")
	public Map<String, String> setActid2ActInfo(@RequestBody ReliableActivityInfo reliableActivityInfo) {
		Map<String, String> map = new HashMap<String, String>();
		if (reliableActInfoServer.setActInfo(reliableActivityInfo)) {
			map.put("success", "存入成功");
		} else {
			map.put("error_msg", "已存在");
		}
		return map;
	}

	@RequestMapping("/get/all")
	public List<ReliableActivityInfo> getAllActInfo() {
		return reliableActInfoServer.getAllActInfo();
	}

	@RequestMapping("/get")
	public ReliableActivityInfo getActid2ActInfo(@RequestParam(value = "actid", required = true) String actid) {
		return reliableActInfoServer.getActInfo(actid);
	}

}
