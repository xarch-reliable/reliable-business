package org.xarch.reliable.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xarch.reliable.service.OpenidCacheServer;
import org.xarch.reliable.service.OpenidManagerServer;

@Service
public class OpenidManagerServerImpl implements OpenidManagerServer{

	private static final Logger logger = LoggerFactory.getLogger(OpenidManagerServerImpl.class);
	
	@Autowired 
	private OpenidCacheServer openidCacheServer;
	
	@Override
	public Map<String, String> addOpenid2Actid(String openid, String actid) {
		Map<String, String> resmap = new HashMap<String, String>();
		Map<String, String> openidmap = openidCacheServer.getOpenidMap(openid, new HashMap<String, String>());
		String addMsg = openidmap.get(actid);
		if(addMsg == null) {
			openidmap.put(actid, "false");
			openidCacheServer.setOpenidMap(openid, openidmap);
			resmap.put("success_msg", "actid_manager加入成功");
			logger.info("[success_msg]"+"actid_manager加入成功");
		}else {
			resmap.put("error_msg", "actid_manager重复加入");
			logger.info("[error_msg]"+"actid_manager重复加入");
		}
		return resmap;
	}

	@Override
	public Map<String, String> updateOpenid2ActidCheck(String openid, String actid) {
		Map<String, String> resmap = new HashMap<String, String>();
		Map<String, String> openidmap = openidCacheServer.getOpenidMap(openid, new HashMap<String, String>());
		String checkMsg = openidmap.get(actid);
		if(checkMsg.equals("true")) {
			resmap.put("error_msg", "openid_manager签到失败");
			logger.info("[error_msg]"+"openid_manager签到失败");
		}else {
			openidmap.put(actid, "true");
			openidCacheServer.setOpenidMap(openid, openidmap);
			resmap.put("success_msg", "openid_manager签到成功");
			logger.info("[success_msg]"+"openid_manager签到成功");
		}
		return resmap;
	}

	@Override
	public Map<String, String> getOpenidActid2Map(String openid) {
		return openidCacheServer.getOpenidMap(openid, new HashMap<String, String>());
	}

}
