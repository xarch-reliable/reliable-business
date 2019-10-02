package org.xarch.reliable.sevice.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xarch.reliable.sevice.BillinfoServer;
import org.xarch.reliable.util.RedisUtil;

import com.google.common.collect.Lists;



@Service
public class BillinfoServerImpl implements BillinfoServer {
	private static final Logger logger = LoggerFactory.getLogger(BillinfoServerImpl.class);
	
	 @Autowired private RedisUtil redisUtil;

	@Override
	public Map<String, Object> setBillinfo(String openid, Map<String, Object> billdata) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		
		if(redisUtil.lSet(openid, billdata)) {
			map.put("success_msg", "true");
		}else {
			map.put("error_msg", "false");
		}
		return map;
	}

	@Override
	public Map<String, Object> getBillinfo(String openid) {
		Map<String, Object> resmap = new HashMap<String, Object>();
		List<Object> billlist = redisUtil.lGet(openid, 0, -1);
		logger.info("billlist===="+billlist);
		resmap.put("billlist", billlist);
		return resmap;
		
	}
	 
}
