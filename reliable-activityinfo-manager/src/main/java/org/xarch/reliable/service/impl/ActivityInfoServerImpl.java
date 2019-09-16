package org.xarch.reliable.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xarch.reliable.service.ActivityInfoServer;
//import org.xarch.reliable.service.feign.FeignActidManager;
import org.xarch.reliable.service.feign.FeignOpenidManager;
import org.xarch.reliable.util.RedisUtil;

import com.google.common.collect.Lists;

@Service
public class ActivityInfoServerImpl implements ActivityInfoServer {

	//@Autowired
	//private FeignActidManager feignActidManager;

	@Autowired
	private FeignOpenidManager feignOpenidManager;
	
	@Autowired
    private RedisUtil redisUtil;
	
	@Override
	public boolean setActivityInfo(String actid, Map<String, Object> actdata) {
		return redisUtil.hmset(actid, actdata);
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
		
		Map<String, String> openid2actid = feignOpenidManager.getOM(openid);
		for (String actid : openid2actid.keySet()) {
			Map maptmp = redisUtil.hmget(actid);
			if( ((String)((Map<String, Object>)maptmp).get("clear")).equals("false") ) {
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
	public boolean setActClear(String actid) {
		
		if( ((String)redisUtil.hget(actid, "clear")).equals("true") ) {
			return false;
		}else {
			Map<String, Object> maptmp = getActivityInfo(actid);
			maptmp.put("clear", "true");
			return setActivityInfo(actid, maptmp);
		}
	}

}
