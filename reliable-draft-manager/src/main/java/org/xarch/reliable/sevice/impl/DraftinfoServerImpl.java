package org.xarch.reliable.sevice.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xarch.reliable.sevice.DraftinfoServer;
import org.xarch.reliable.util.RedisUtil;

import com.google.common.collect.Lists;



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
	public Map<String, Object> getDraftinfo(List<String> draftidset) {
		Map<String, Object> resmap = new HashMap<String, Object>();
		List<Map<String, Object>> draftlist = Lists.newArrayList();
		// TODO Auto-generated method stubsss
		logger.info("draftidset===="+draftidset);
		for (String draftid : draftidset) {
		      //System.out.println(draftid);
			//Map draftmap = redisUtil.hmget(String.valueOf(draftid));
			Map draftmap = redisUtil.hmget(draftid);
			if( (draftmap)!=null){
				draftlist.add(draftmap);
			}else {
				draftmap.put("error_msg", "草稿信息为空");
				draftlist.add(draftmap);
			}
			
			logger.info("DraftidinfoServerImpl() :: getDraftidinfo : draftid="+draftid);
			
		}
		resmap.put("draftlist", draftlist);
		return resmap;
		
	}
	 
}
