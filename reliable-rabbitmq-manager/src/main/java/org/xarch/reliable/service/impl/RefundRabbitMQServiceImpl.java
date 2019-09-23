package org.xarch.reliable.service.impl;

import java.awt.List;
import java.util.Collection;
import java.util.Map;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xarch.reliable.service.RefundRabbitMQService;

/**
*
*  @Description  RefundRabbitMQService实现类
*
*  @Author  Jim_Carrey
*
*  @Date  2019年9月23日
*/
@Service
@RabbitListener(queues = "refund")
public class RefundRabbitMQServiceImpl implements RefundRabbitMQService{
	
		@Autowired
		private AmqpTemplate rabbitmqTemplate;

		@Override
		public void sendRefundMessageToQueue(Map<String, List> refundMessage) {
			rabbitmqTemplate.convertAndSend("refund", refundMessage);
			
		}

		@Override
		@RabbitHandler
		public Collection<List> receiveRefundMessageFromQueue(Map<String, List> refundMessage) {
			return refundMessage.values();
		} 
		
		

}
