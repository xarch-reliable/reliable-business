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
public interface OrderResponseService {
	
	public String setOrderResponse(String payid,Map<String, Object> oResponseData);
	
	public Map<String, Object> getOrderResponse(String payid);

}
