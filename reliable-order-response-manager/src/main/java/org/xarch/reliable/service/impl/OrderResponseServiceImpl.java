package org.xarch.reliable.service.impl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xarch.reliable.service.OrderResponseService;
import org.xarch.reliable.util.RedisUtil;

/**
*
*  @Description  Implementation of interface service
*
*  @Author  Jim_Carrey
*
*  @Date  2019年9月19日
*/
@Service
public class OrderResponseServiceImpl implements OrderResponseService{
	
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(OrderResponseServiceImpl.class);
	
	@Autowired
    private RedisUtil redisUtil;

	@Override
	public String setOrderResponse(String prepay_id, Map<String, Object> oResponseData) {
		if(redisUtil.hmset(prepay_id, oResponseData)) {
			return "true";
		}else {
			return "false";
		}
	}

	@SuppressWarnings({ "unchecked", "null" })
	@Override
	public Map<String, Object> getOrderResponse(String prepay_id) {
		@SuppressWarnings("rawtypes")
		Map orderResponseMap = redisUtil.hmget(prepay_id);
		if (orderResponseMap == null) {
			orderResponseMap.put("error_msg", "无此prepay_id的订单响应信息");
		}
		return (Map<String, Object>)orderResponseMap;
	}

}
