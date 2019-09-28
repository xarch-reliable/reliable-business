package org.xarch.reliable.sevice.impl;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xarch.reliable.sevice.DraftinfoServer;
import org.xarch.reliable.util.RedisUtil;



@Service
public class DraftinfoServerImpl implements DraftinfoServer {
	private static final Logger logger = LoggerFactory.getLogger(DraftinfoServerImpl.class);
	
	 @Autowired private RedisUtil redisUtil;

	@Override
	public Map<String, Object> setDraftinfo(String draftid, Map<String, Object> draftdata) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		if(redisUtil.hmset(draftid, draftdata)) {
			map.put("success_msg", "true");
		}else {
			map.put("error_msg", "false");
		}
		return map;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Map<String, Object> getDraftinfo(String draftid) {
		// TODO Auto-generated method stub
		Map draftdata = redisUtil.hmget(draftid);
		return (Map<String, Object>)draftdata;
		
	}

	
	/*
	 * @Override public Map<String, Object> setDraftidinfo(String openid, String
	 * draftid) { // TODO Auto-generated method stub Map<String, Object>
	 * openid2draftidmap = new HashMap<String, Object>(); Map<String, Object> map =
	 * new HashMap<String, Object>(); openid2draftidmap.put(draftid, null);
	 * if(redisUtil.hmset(openid, openid2draftidmap)) { map.put("success_msg",
	 * "true"); }else { map.put("error_msg", "false"); } return map; }
	 * 
	 * @SuppressWarnings({ "unchecked", "rawtypes" })
	 * 
	 * @Override public Map<String, Object> getDraftidinfo(String openid) { // TODO
	 * Auto-generated method stub Map draftiddata = redisUtil.hmget(openid); return
	 * (Map<String, Object>)draftiddata; }
	 */
	 
}
