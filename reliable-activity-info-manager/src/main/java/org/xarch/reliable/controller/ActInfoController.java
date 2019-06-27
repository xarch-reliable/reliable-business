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
import org.xarch.reliable.utils.BaseResultTools;

import com.google.common.collect.Lists;

@RestController
@RequestMapping("/activity/info")
public class ActInfoController {

	@Autowired
	private ReliableActInfoServer reliableActInfoServer;

	@PostMapping("/set")
	public Map<String, String> setActid2ActInfo(@RequestBody String actInfoStr) {
		ReliableActivityInfo reliableActivityInfo = BaseResultTools.fromJSON(actInfoStr, ReliableActivityInfo.class);
		Map<String, String> map = new HashMap<String, String>();
		if (reliableActivityInfo.getActid() == null) {
			map.put("error_msg", "actid为空");
			return map;
		}
		if (reliableActInfoServer.createActInfo(reliableActivityInfo)) {
			map.put("success_msg", "存入成功");
		} else {
			map.put("error_msg", "已存在");
		}
		return map;
	}

	@RequestMapping("/get/all")
	public List<Map<String, Object>> getAllActInfo() {
		List<Map<String, Object>> list = Lists.newArrayList();
		for (ReliableActivityInfo actInfo : reliableActInfoServer.getAllActInfo()) {
			list.add(BaseResultTools.ObjectToMap(actInfo));
		}
		return list;
	}

	@RequestMapping("/get")
	public Map<String, Object> getActid2ActInfo(@RequestParam(value = "actid", required = true) String actid) {
		return BaseResultTools.ObjectToMap(reliableActInfoServer.getActInfo(actid));
	}
	
	@RequestMapping("/finish")
	public Map<String, String> finishActid2ActInfo(@RequestParam(value = "actid", required = true) String actid) {
		Map<String, String> resmap = new HashMap<String, String>();
		ReliableActivityInfo actinfo = reliableActInfoServer.getActInfo(actid);
		if(reliableActInfoServer.fininshActInfo(actinfo)) {
			resmap.put("success_msg", "活动结算数据储存成功");
		}else {
			resmap.put("error_msg", "活动结算数据储存失败");
		}
		return resmap;
	}

}
