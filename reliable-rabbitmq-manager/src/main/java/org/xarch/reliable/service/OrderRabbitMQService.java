package org.xarch.reliable.service;
/**
*
*  @Description  订单消息队列接口
*
*  @Author  Jim_Carrey
*
*  @Date  2019年9月22日
*/
public interface OrderRabbitMQService {
 
	 public void  sendOrderMessageToQueue(Object message);
	
	 public Object receiveOrderMessageFromQueue(Object message);
}
