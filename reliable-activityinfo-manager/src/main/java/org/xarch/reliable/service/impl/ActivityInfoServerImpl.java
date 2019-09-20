package org.xarch.reliable.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xarch.reliable.service.ActivityInfoServer;
import org.xarch.reliable.service.feign.FeignActidManager;
//import org.xarch.reliable.service.feign.FeignActidManager;
import org.xarch.reliable.service.feign.FeignOpenidManager;
import org.xarch.reliable.util.BaseResultTools;
import org.xarch.reliable.util.RedisUtil;

import com.google.common.collect.Lists;

@Service
public class ActivityInfoServerImpl implements ActivityInfoServer {
	
	private static final Logger logger = LoggerFactory.getLogger(ActivityInfoServerImpl.class);

	@Autowired
	private FeignActidManager feignActidManager;

	@Autowired
	private FeignOpenidManager feignOpenidManager;
	
	@Autowired
    private RedisUtil redisUtil;
	
	@Override
	public String setActivityInfo(String actid, Map<String, Object> actdata) {
		if(redisUtil.hmset(actid, actdata)) {
			return "true";
		}else {
			return "false";
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Map<String, Object> getActivityInfo(String actid) {	
		Map actinfodata = redisUtil.hmget(actid);
		return (Map<String, Object>)actinfodata;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Map<String, Object> getActInfoListByOpenid(String openid) {
		
		Map<String, Object> resmap = new HashMap<String, Object>();
		List<Map<String, Object>> activityDoneList = Lists.newArrayList();
		List<Map<String, Object>> activityUnDoneList = Lists.newArrayList();
		logger.info("[openid]"+openid);
		Map<String, String> openid2actid = feignOpenidManager.getOM(openid);
		for (String actid : openid2actid.keySet()) {
			Map maptmp = redisUtil.hmget(actid);
			if( (((Map<String, Object>)maptmp).get("clear")).equals("false") ) {
				activityUnDoneList.add(maptmp);
			}else {
				activityDoneList.add(maptmp);
			}
		}
		resmap.put("activityDoneList", activityDoneList);
		resmap.put("activityUnDoneList", activityUnDoneList);
		return resmap;
	}

	@Override
	public String setActClear(String actid) {
		
		if( ((String)redisUtil.hget(actid, "clear")).equals("true") ) {
			return "false";
		}else {
			Map<String, Object> maptmp = getActivityInfo(actid);
			maptmp.put("clear", "true");
			return setActivityInfo(actid, maptmp);
		}
	}

	@Override
	public String getActClear(String actid) {
		
		return (String)redisUtil.hget(actid, "clear");
	}

	@Override
	public String addActPartNumber(String actid) {
		
		Map<String, Object> maptmp = getActivityInfo(actid);
		int i = Integer.parseInt((String)maptmp.get("part_number"));
		++i;
		maptmp.put("part_number", String.valueOf(i));
		return setActivityInfo(actid, maptmp);
		
	}

	@Override
	public String setActStatus(String openid, String actid, String status) {
		
		Map<String, Object> maptmp = getActivityInfo(actid);
		String creator_openid = (String)maptmp.get("creator_openid");
		if(creator_openid.equals(openid)) {
			maptmp.put("status", status);
			return setActivityInfo(actid, maptmp);
		}else {
			return "false";
		}
	}

}
