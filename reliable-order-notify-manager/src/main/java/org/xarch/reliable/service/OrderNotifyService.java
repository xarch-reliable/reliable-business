package org.xarch.reliable.service;

import java.util.Map;

/**
*
*  @Description  Service
*
*  @Author  Jim_Carrey
*
*  @Date  2019年9月19日
*/
public interface OrderNotifyService {
	
	public String setOrderNotify(String out_trade_no,Map<String, Object> oNotifyData);
	
	public Map<String, Object> getOrderNotify(String out_trade_no);
	
}
