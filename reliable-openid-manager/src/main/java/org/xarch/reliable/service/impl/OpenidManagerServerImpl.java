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
		Map<String, String> resmap = new HashMap<String, String>();
		Map<String, String> map = openidCacheServer.getOpenidMap(openid, new HashMap<String, String>());
		map.put(actid, "false");
		openidCacheServer.setOpenidMap(openid, map);
		resmap.put("success_msg", "openid_manager创建成功");
		return resmap;
	}

	@Override
	public Map<String, String> updateOpenid2ActidCheck(String openid, String actid) {
		Map<String, String> resmap = new HashMap<String, String>();
		Map<String, String> openidmap = openidCacheServer.getOpenidMap(openid, new HashMap<String, String>());
		String checkMsg = openidmap.get(actid);
		if(checkMsg.equals("true")) {
			resmap.put("error_msg", "openid_manager签到失败");
		}else {
			openidmap.put(actid, "true");
			openidCacheServer.setOpenidMap(openid, openidmap);
			resmap.put("success_msg", "openid_manager签到成功");
		}
		return resmap;
	}

	@Override
	public Map<String, String> getOpenidActid2Map(String openid) {
		return openidCacheServer.getOpenidMap(openid, new HashMap<String, String>());
	}

}
