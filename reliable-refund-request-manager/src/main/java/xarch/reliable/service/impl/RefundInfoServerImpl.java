package xarch.reliable.service.impl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xarch.reliable.service.RefundInfoServer;
import xarch.reliable.util.RedisUtil;

@Service
public class RefundInfoServerImpl implements RefundInfoServer {

	private static final Logger logger = LoggerFactory.getLogger(RefundInfoServerImpl.class);

	/*
	 * @Autowired private FeignActidManager feignActidManager;
	 * 
	 * @Autowired private FeignOpenidManager feignOpenidManager;
	 */
	
	 @Autowired private RedisUtil redisUtil;
	 
	
	@Override
	public String setRefundInfo(String payid, Map<String, Object> refunddata) {
		if(redisUtil.hmset(payid, refunddata)) {
			return "true";
		}else {
			return "false";
		}
	}

	@Override
	public Map<String, Object> getRefundInfo(String payid) {
		Map refundinfodata = redisUtil.hmget(payid);
		return (Map<String, Object>)refundinfodata;
	}


}
