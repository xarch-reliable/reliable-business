package org.xarch.reliable.service.impl;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xarch.reliable.service.OrderRabbitMQService;

/**
*
*  @Description  OrderRabbitMQService实现类
*
*  @Author  Jim_Carrey
*
*  @Date  2019年9月22日
*/
@Service
@RabbitListener(queues = "order")
public class OrderRabbitMQServiceImpl implements OrderRabbitMQService{
	
		@Autowired
		private AmqpTemplate rabbitmqTemplate;
		
		@Override
		public void sendOrderMessageToQueue(Object message) {
			rabbitmqTemplate.convertAndSend(message);
		}

		@Override
		@RabbitHandler
		public Object receiveOrderMessageFromQueue(Object message) {
			return message;
		}

}
