package org.xarch.reliable.service;
/**
*
*  @Description  活动消息队列接口
*
*  @Author  Jim_Carrey
*
*  @Date  2019年9月22日
*/
public interface ActivityRabbitMQService {
	
	 public void  sendActMessageToQueue(Object message);
	 
	 public Object receiveActMessageFromQueue(Object message);
}
