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
		Map<String, String> map = actidCacheServer.getActidMap(actid, new HashMap<String, String>());
		map.put(openid, "false");
		return actidCacheServer.setActidMap(actid, map);
	}

	@Override
	public Map<String, String> updateActid2OpenidCheck(String actid, String openid) {
		Map<String, String> map = actidCacheServer.getActidMap(actid, new HashMap<String, String>());
		map.put(openid, "true");
		return actidCacheServer.setActidMap(actid, map);
	}

	@Override
	public Map<String, String> getActid2Openid2Map(String actid) {
		return actidCacheServer.getActidMap(actid, new HashMap<String, String>());
	}

}
