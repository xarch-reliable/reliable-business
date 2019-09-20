package xarch.reliable.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xarch.reliable.service.RefundInfoServer;
import xarch.reliable.utils.RedisUtil;

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
	public Map<String, Object> setRefundInfo(String out_refund_no, Map<String, Object> refunddata) {
		Map<String, Object> map = new HashMap<String, Object>();
		if(redisUtil.hmset(out_refund_no, refunddata)) {
			map.put("success_msg", "true");
		}else {
			map.put("error_msg", "false");
		}
		return map;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Map<String, Object> getRefundInfo(String out_refund_no) {
		Map refundinfodata = redisUtil.hmget(out_refund_no);
		return (Map<String, Object>)refundinfodata;
	}

}
