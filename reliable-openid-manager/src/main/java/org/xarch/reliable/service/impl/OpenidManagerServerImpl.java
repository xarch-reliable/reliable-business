package org.xarch.reliable.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xarch.reliable.service.OpenidCacheServer;
import org.xarch.reliable.service.OpenidManagerServer;

@Service
public class OpenidManagerServerImpl implements OpenidManagerServer{

	@Autowired 
	private OpenidCacheServer openidCacheServer;
	
	@Override
	public Map<String, String> addOpenid2Actid(String openid, String actid) {
		Map<String, String> map = openidCacheServer.getOpenidMap(openid, new HashMap<String, String>());
		map.put(actid, "false");
		return openidCacheServer.setOpenidMap(openid, map);
	}

	@Override
	public Map<String, String> updateOpenid2ActidCheck(String openid, String actid) {
		Map<String, String> map = openidCacheServer.getOpenidMap(openid, new HashMap<String, String>());
		map.put(actid, "true");
		return openidCacheServer.setOpenidMap(openid, map);
	}

	@Override
	public Map<String, String> getOpenidActid2Map(String openid) {
		return openidCacheServer.getOpenidMap(openid, new HashMap<String, String>());
	}

}
