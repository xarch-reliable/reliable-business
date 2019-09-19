package org.xarch.reliable.service;

import java.util.Map;

/**
*
*  @Description  Service
*
*  @Author  Jim_Carrey
*
*  @Date  2019年9月18日
*/
public interface OrderRequestService {
	
	public String setOrderRequest(String out_trade_no,Map<String, Object> oRequestData);
	
	public Map<String, Object> getOrderRequest(String out_trade_no);
	
	

}
