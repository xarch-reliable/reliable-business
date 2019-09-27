package org.xarch.reliable.service.impl;

import java.util.HashMap;
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
	public Map<String, Object> setDraftidinfo(String openid, String draftid, Map<String, Object> data) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> draftmap = new HashMap<String, Object>();
		draftmap.put(draftid, data);
		
		  if(redisUtil.sSet(openid, draftid)) { 
			  
			  map.put("success_msg", "true");
			  
		}else {
			
			map.put("error_msg", "false");
		  
		}
		 
		/*
		 * if(redisUtil.hmset(openid, draftmap)){ map.put("success_msg", "true"); }else
		 * { map.put("error_msg", "false"); }
		 */
		return map;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> getDraftidinfo(String openid) {
		// TODO Auto-generated method stub
		@SuppressWarnings("rawtypes")
		Set draftidmap = redisUtil.sGet(openid);
		return (Map<String, Object>)draftidmap;
		
	}

}
