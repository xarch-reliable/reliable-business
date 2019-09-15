package org.xarch.reliable.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xarch.reliable.service.DataActionServer;
import org.xarch.reliable.service.feign.FeignActInfoManager;
import org.xarch.reliable.service.feign.FeignActidManager;
import org.xarch.reliable.service.feign.FeignJsapiManager;
import org.xarch.reliable.service.feign.FeignOpenidManager;
import org.xarch.reliable.service.feign.FeignPayManager;
import org.xarch.reliable.service.feign.FeignPayidManager;
import org.xarch.reliable.service.thread.ThreadPool;

import com.google.common.collect.Lists;

@Service
public class DataActionServerImpl implements DataActionServer{

	@Autowired
	private FeignActInfoManager feignActInfoManager;

	@Autowired
	private FeignActidManager feignActidManager;

	@Autowired
	private FeignOpenidManager feignOpenidManager;

	@Autowired
	private FeignPayidManager feignPayidManager;

	@Autowired
	private FeignPayManager feignPayManager;
	
	@Autowired
	private FeignJsapiManager feignJsapiManager;

	//线程管理者
	@Autowired
	private ThreadPool threadPool;
	
	@Override
	public Map<String, Object> onGetActinfoListByOpenid(String openid) {
		Map<String, Object> resmap = new HashMap<String, Object>();
		List<Map<String, Object>> activityDoneList = Lists.newArrayList();
		List<Map<String, Object>> activityUnDoneList = Lists.newArrayList();
		List<Map<String, Object>> allactinfo = feignActInfoManager.getAllActInfo();
		Map<String, String> openid2actid = feignOpenidManager.getOM(openid);
		
		for (Map<String, Object> actinfo :allactinfo) {
			for (String actid : openid2actid.keySet()) {
				String info2actid = (String) actinfo.get("actid");
				if(info2actid !=null && info2actid.equals(actid)) {
					if(((String)actinfo.get("clear")).equals("false")) {
						activityUnDoneList.add(actinfo);
					}else {
						activityDoneList.add(actinfo);
					}
				}
			}
		}
		resmap.put("activityDoneList", activityDoneList);
		resmap.put("activityUnDoneList", activityUnDoneList);
		
		return resmap;
	}

	@Override
	public Map<String, String> onGetActidListByOpenid(String openid) {
		return feignOpenidManager.getOM(openid);
	}

	@Override
	public Map<String, String> onGetOpenidListByActid(String actid) {
		return feignActidManager.getAM(actid);
	}

	@Override
	public Map<String, Object> onGetActinfoByActid(String actid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> onSetOpenid2ActidList(String openid, String actid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> onSetActid2OpenidList(String actid, String openid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> onSetActinfoByBody(String actid, Map<String, String> data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> onCheckOpenid2ActidList(String openid, String actid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> onCheckActid2OpenidList(String actid, String openid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String onDefault() {
		// TODO Auto-generated method stub
		return null;
	}

}
