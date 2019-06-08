package org.xarch.reliable.service.impl;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.xarch.reliable.service.OpenidCacheServer;

@Service
public class OpenidCacheServerImpl implements OpenidCacheServer{

	@Override
	public Map<String, String> setOpenidMap(String openid, Map<String, String> map) {
		return map;
	}

	@Override
	public Map<String, String> getOpenidMap(String openid, Map<String, String> map) {
		return map;
	}


}
