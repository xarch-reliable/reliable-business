package org.xarch.reliable.service.impl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xarch.reliable.service.RefundInfoServer;
import org.xarch.reliable.util.RedisUtil;



@Service
public class RefundInfoServerimpl implements RefundInfoServer {
	private static final Logger logger = LoggerFactory.getLogger(RefundInfoServerimpl.class);
	
	@Autowired private RedisUtil redisUtil;

	@Override
	public String setRefundInfo(String out_refund_no, Map<String, Object> notifydata) {
		if(redisUtil.hmset(out_refund_no, notifydata)) {
			return "true";
		}else {
			return "false";
		}
	}

	@Override
	public Map<String, Object> getRefundInfo(String out_refund_no) {
		Map notifydata = redisUtil.hmget(out_refund_no);
		return (Map<String, Object>)notifydata;
	}

}
