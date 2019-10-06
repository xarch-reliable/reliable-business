package org.xarch.reliable.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xarch.reliable.service.ActivityInfoServer;
import org.xarch.reliable.service.feign.FeignActidManager;
import org.xarch.reliable.service.feign.FeignDataManager;
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
	private FeignDataManager feignDataManager;
	@Autowired
    private RedisUtil redisUtil;
	
	@Override
	public Map<String, Object> setActivityInfo(String actid, Map<String, Object> actdata) {
		Map<String, Object> resmap = new HashMap<String, Object>();
		if(redisUtil.hmset(actid, actdata)) {
			resmap.put("success_msg", "true");
		}else {
			resmap.put("error_msg", "false");
		}
		return resmap;
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
	//////////////////////////////////////////////
	@SuppressWarnings({ "unchecked", "rawtypes", "unused" })
	@Override
	public Map<String, Object> getActInfoListByActidlist(List<Object> actlist) {
		
		List<String> actList = (List<String>)(List)actlist;
		Map<String, Object> resmap = new HashMap<String, Object>();
		List<Map<String, Object>> ActList = Lists.newArrayList();
		logger.info("[actlist]"+actlist);
		for (String actid : actList) {
			
			//Map maptmp = redisUtil.hmget(String.valueOf(actid));
			Map maptmp = redisUtil.hmget(actid);
				ActList.add(maptmp);
		}
		resmap.put("ActList", ActList);
		return resmap;
	}

	@Override
	public Map<String, Object> setActClear(String actid) {
		if( ((String)redisUtil.hget(actid, "clear")).equals("true") ) {
			Map<String, Object> resmap = new HashMap<String, Object>();
			resmap.put("error_msg", "false");
			return resmap;
		}else {
			Map<String, Object> maptmp = getActivityInfo(actid);
			maptmp.put("clear", "true");
			return setActivityInfo(actid, maptmp);
		}
	}

	@Override
	public Map<String, Object> getActClear(String actid) {
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap.put("clear", (String)redisUtil.hget(actid, "clear"));
		return resmap;
	}
	
	@Override
	public Map<String, Object> getcheck(String actid) {
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap.put("check_staus", (String)redisUtil.hget(actid, "check_staus"));
		return resmap;
	}
	
	@Override
	public Map<String, Object> setcheck(String actid) {
		if( ((String)redisUtil.hget(actid, "check_staus")).equals("true") ) {
			Map<String, Object> resmap = new HashMap<String, Object>();
			resmap.put("error_msg", "false");
			return resmap;
		}else {
			Map<String, Object> maptmp = getActivityInfo(actid);
			maptmp.put("check_staus", "true");
			return setActivityInfo(actid, maptmp);
		}
	}

	@Override
	public Map<String, Object> addActPartNumber(String actid) {
		
		Map<String, Object> maptmp = getActivityInfo(actid);
		int i = Integer.parseInt((String)maptmp.get("part_number"));
		++i;
		maptmp.put("part_number", String.valueOf(i));
		return setActivityInfo(actid, maptmp);
		
	}

	@Override
	public Map<String, Object> setActStatus(String openid, String actid, String status) {
		
		Map<String, Object> maptmp = getActivityInfo(actid);
		String creator_openid = (String)maptmp.get("creator_openid");
		if(creator_openid.equals(openid)) {
			maptmp.put("status", status);
			return setActivityInfo(actid, maptmp);
		}else {
			Map<String, Object> resmap = new HashMap<String, Object>();
			resmap.put("error_msg", "false");
			return resmap;
		}
	}

	@Override
	public Map<String, Object> getActTotalFee(String actid) {
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap.put("baozhenghb", (String)redisUtil.hget(actid, "baozhenghb"));
		return resmap;
	}

	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Map<String, Object> getAllactid() {
		logger.info("[getAllactid]"+redisUtil.myhmget());
		List list = new ArrayList(redisUtil.myhmget());
		
		List<String> actList = (List<String>)list;
		Map<String, Object> resmap = new HashMap<String, Object>();
		List<Object> ActList = Lists.newArrayList();
		
		int size = actList.size();//活动总数
		int u_size = 0;//未完成活动数
		int d_size = 0;//已完成活动数
		int bzj = 0;//保证金总额
		int d_bzj = 0;//已完成保证金
		int ud_bzj = 0;//未完成保证金，资金沉淀
		int ur_bzj = 0;//违约金总额
		int r_bzj = 0;//退回金额
		for (String actid : actList) {
			Map maptmp = redisUtil.hmget(actid);
			Map<String, Object> map =(Map<String, Object>)maptmp;
			if( (map.get("clear")).equals("false") ) {
				ud_bzj = ud_bzj + Integer.parseInt((String)map.get("baozhenghb"))*Integer.parseInt((String)map.get("part_number"));
				u_size = u_size +1;
			}else {
				
				Map<String, Object> sendpayidmap = new HashMap<String, Object>();
				Map<String, Object> payidtmp = new HashMap<String, Object>();
				payidtmp.put("actid", actid);
				sendpayidmap.put("xrdataction", "getpayidMap");
				sendpayidmap.put("data", payidtmp);
				Map<String, String> payidmap = (Map<String, String>)feignDataManager.doSupport2DataCenter(sendpayidmap).get("body");
				
				
				Map<String, Object> sendmap1 = new HashMap<String, Object>();
				Map<String, Object> datatmp1 = new HashMap<String, Object>();
				datatmp1.put("actid", actid);
				sendmap1.put("xrdataction", "getAMList");
				sendmap1.put("data", datatmp1);
				Map<String, String> actidmap = (Map<String, String>)feignDataManager.doSupport2DataCenter(sendmap1).get("body");
				
								
				Map<String, String> ReliableMap = new HashMap<String, String>();
				Map<String, String> UnReliableMap = new HashMap<String, String>();
				for (Entry<String, String> entry: actidmap.entrySet()) {
					if(entry.getValue().equals("true")) {
						ReliableMap.put(entry.getKey(), payidmap.get(entry.getKey()));
					}else {
						UnReliableMap.put(entry.getKey(), payidmap.get(entry.getKey()));
					}
				}
				r_bzj = r_bzj + ReliableMap.size()*Integer.parseInt((String)map.get("baozhenghb"));
				ur_bzj = ur_bzj + UnReliableMap.size()*Integer.parseInt((String)map.get("baozhenghb"));
				d_bzj = d_bzj +Integer.parseInt((String)map.get("baozhenghb"))*Integer.parseInt((String)map.get("part_number"));
				
				d_size = d_size +1;
				
			}
				ActList.add(map);
		}
		bzj = ud_bzj + d_bzj;
		resmap.put("size", size);//活动总数
		resmap.put("u_size", u_size);//未完成活动数
		resmap.put("d_size", d_size);//已完成活动数
		resmap.put("bzj", bzj);//保证金总额
		resmap.put("ud_bzj", ud_bzj);//未完成保证金，资金沉淀
		resmap.put("d_bzj", d_bzj);//已完成保证金
		resmap.put("r_bzj", r_bzj);//退回金额
		resmap.put("ur_bzj", ur_bzj);//违约金总额
		logger.info("[getAllactid]"+resmap);
		return resmap;
		
	}
}
