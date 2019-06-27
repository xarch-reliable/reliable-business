package org.xarch.reliable.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xarch.reliable.service.ActidCacheServer;
import org.xarch.reliable.service.ActidManagerServer;

@Service
public class ActidManagerServerImpl implements ActidManagerServer {

	@Autowired
	private ActidCacheServer actidCacheServer;

	@Override
	public Map<String, String> addActid2Openid(String actid, String openid) {
		Map<String, String> resmap = new HashMap<String, String>();
		Map<String, String> map = actidCacheServer.getActidMap(actid, new HashMap<String, String>());
		map.put(openid, "false");
		actidCacheServer.setActidMap(actid, map);
		resmap.put("success_msg", "actid_manager创建成功");
		return resmap;
	}

	@Override
	public Map<String, String> updateActid2OpenidCheck(String actid, String openid) {
		Map<String, String> resmap = new HashMap<String, String>();
		Map<String, String> actidmap = actidCacheServer.getActidMap(actid, new HashMap<String, String>());
		String checkMsg = actidmap.get(openid);
		if(checkMsg.equals("true")) {
			resmap.put("error_msg", "actid_manager签到失败");
		}else {
			actidmap.put(openid, "true");
			actidCacheServer.setActidMap(actid, actidmap);
			resmap.put("success_msg", "actid_manager签到成功");
		}
		return resmap;
	}

	@Override
	public Map<String, String> getActid2Openid2Map(String actid) {
		return actidCacheServer.getActidMap(actid, new HashMap<String, String>());
	}

}
