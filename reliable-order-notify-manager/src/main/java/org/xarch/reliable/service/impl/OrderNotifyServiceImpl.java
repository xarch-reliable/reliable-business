package org.xarch.reliable.service.impl;
/**
*
*  @Description  类描述
*
*  @Author  Jim_Carrey
*
*  @Date  2019年9月19日
*/

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xarch.reliable.service.OrderNotifyService;
import org.xarch.reliable.util.RedisUtil;
@Service
public class OrderNotifyServiceImpl implements OrderNotifyService{
	
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(OrderNotifyService.class);
	
	@Autowired
    private RedisUtil redisUtil;

	@Override
	public String setOrderNotify(String out_trade_no, Map<String, Object> oNotifyData) {
		
		if(redisUtil.hmset(out_trade_no, oNotifyData)) {
			return "true";
		}else {
			return "false";
		}
	}

	@Override
	public Map<String, Object> getOrderNotify(String out_trade_no) {
		@SuppressWarnings("rawtypes")
		Map orderNotifyMap = redisUtil.hmget(out_trade_no);
		if (orderNotifyMap == null) {
			orderNotifyMap.put("error_msg", "无此out_trade_no的订单通知信息");
		}
		return (Map<String, Object>)orderNotifyMap;
	}

}
