package org.xarch.reliable.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.xarch.reliable.service.ActivityRabbitMQService;
import org.xarch.reliable.service.OrderRabbitMQService;

/**
*
*  @Description  Dispatching for different message queue
*
*  @Author  Jim_Carrey
*
*  @Date  2019年9月22日
*/
@RestController
@RequestMapping("/message/queue")
public class RabbitMQController {
	
	private static final Logger logger = LoggerFactory.getLogger(RabbitMQController.class);

	@Autowired
	private OrderRabbitMQService oRMQService;
	
	@Autowired
	private ActivityRabbitMQService aRMQService;
	
	@RequestMapping("order/send")
	public void sendOrderMessage(@RequestParam(value = "orderMessage",required = true)Object orderMessage) {
		logger.info("发送到order消息队列的信息：", orderMessage);
		oRMQService.sendOrderMessageToQueue(orderMessage);
	}
	
	@RequestMapping("order/receive")
	public Object receiveOrderMessage(@RequestParam(value = "orderMessage",required = false)Object orderMessage,
			@RequestBody Object message) {
		return aRMQService.receiveActMessageFromQueue(orderMessage);
		
	}
	
	@RequestMapping("activity/send")
	public void sendActMessage(@RequestParam(value = "actMessage",required = true)Object actMessage) {
		logger.info("发送到activity消息队列的信息：", actMessage);
		aRMQService.sendActMessageToQueue(actMessage);
	}
	@RequestMapping("activity/receive")
	public Object receiveActMessage(@RequestParam(value = "actMessage",required = false)Object actMessage,
			@RequestBody Object message) {
		return aRMQService.receiveActMessageFromQueue(actMessage);
	}
	
}
