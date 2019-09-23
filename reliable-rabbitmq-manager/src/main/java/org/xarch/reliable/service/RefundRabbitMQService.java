package org.xarch.reliable.service;

import java.awt.List;
import java.util.Collection;
import java.util.Map;

/**
*
*  @Description  退款消息队列接口
*
*  @Author  Jim_Carrey
*
*  @Date  2019年9月23日
*/
public interface RefundRabbitMQService {
 
	 public void  sendRefundMessageToQueue(Map<String, List> refundMessage);
	
	 public Collection<List> receiveRefundMessageFromQueue(Map<String, List> refundMessage);
}
