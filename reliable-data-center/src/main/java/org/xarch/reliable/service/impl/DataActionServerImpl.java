package org.xarch.reliable.service.impl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xarch.reliable.service.DataActionServer;
import org.xarch.reliable.service.feign.FeignActidManager;
import org.xarch.reliable.service.feign.FeignActivityinfoManager;
import org.xarch.reliable.service.feign.FeignJsapiManager;
import org.xarch.reliable.service.feign.FeignOpenidManager;
import org.xarch.reliable.service.feign.FeignPayManager;
import org.xarch.reliable.service.feign.FeignPayidManager;
import org.xarch.reliable.service.thread.ThreadPool;

@Service
public class DataActionServerImpl implements DataActionServer{
	
	private static final Logger logger = LoggerFactory.getLogger(DataActionServer.class);

	@Autowired
	private FeignActivityinfoManager feignActInfoManager;

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
		return feignActInfoManager.getAllActInfo(openid);
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
		return feignActInfoManager.getActInfoByActid(actid);
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
	public String onSetActinfoByBody(Map<String, Object> data) {
		String actid = (String)data.get("actid");
		if(actid != null) {
			return feignActInfoManager.setActInfo(actid, data);
		}else {
			return "false";
		}
	}

	@Override
	public String onSetActClear(String actid) {
		return feignActInfoManager.finishActInfoByActid(actid);
	}
	
	@Override
	public String onGetActClear(String actid) {
		return feignActInfoManager.getActfinishByActid(actid);
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
