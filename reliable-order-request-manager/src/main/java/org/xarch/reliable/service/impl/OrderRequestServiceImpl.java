package org.xarch.reliable.service.impl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xarch.reliable.service.OrderRequestService;
import org.xarch.reliable.util.RedisUtil;

/**
*
*  @Description  Implementation of interface service
*
*  @Author  Jim_Carrey
*
*  @Date  2019年9月18日
*/
@Service
public class OrderRequestServiceImpl implements OrderRequestService{
	
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(OrderRequestServiceImpl.class);
	
	@Autowired
    private RedisUtil redisUtil;

	@Override
	public String setOrderRequest(String out_trade_no, Map<String, Object> oRequestData) {
		if(redisUtil.hmset(out_trade_no, oRequestData)) {
			return "true";
		}else {
			return "false";
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> getOrderRequest(String out_trade_no) {
		@SuppressWarnings("rawtypes")
		Map orderRequestMap = redisUtil.hmget(out_trade_no);
		if (orderRequestMap == null) {
			orderRequestMap.put("error_msg", "无此out_trade_no的订单请求信息");
		}
		return (Map<String, Object>)orderRequestMap;
	}

}
