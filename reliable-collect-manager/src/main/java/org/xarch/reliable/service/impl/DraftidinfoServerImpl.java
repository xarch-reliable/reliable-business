package org.xarch.reliable.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xarch.reliable.service.DraftidinfoServer;
import org.xarch.reliable.util.RedisUtil;

@Service
public class DraftidinfoServerImpl implements DraftidinfoServer {

	private static final Logger logger = LoggerFactory.getLogger(DraftidinfoServerImpl.class);
	@Autowired private RedisUtil redisUtil;
	
	@Override
	public Map<String, Object> setCollectinfo(String openid, String actid) {
		Map<String, Object> map = new HashMap<String, Object>();

		  if(redisUtil.lSet(openid, actid)) { 
			  
			  map.put("success_msg", "true");
			  
		}else {
			
			map.put("error_msg", "false");
		  
		}
		return map;
	}

	@Override
	public Map<String, Object> getCollectinfo(String openid) {
		Map<String, Object> CollectListmap = new HashMap<String, Object>();
		List<Object> CollectList = redisUtil.lGet(openid, 0, -1);
		
		logger.info("DraftidinfoServerImpl() :: getCollectinfo : CollectList="+CollectList);
		CollectListmap.put("CollectList", CollectList);
		return CollectListmap;

	}
}
