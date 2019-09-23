package org.xarch.reliable.controller;

import java.awt.List;
import java.util.Collection;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.xarch.reliable.service.RefundRabbitMQService;

/**
*
*  @Description  Dispatching for different message queue
*
*  @Author  Jim_Carrey
*
*  @Date  2019年9月23日
*/
@RestController
@RequestMapping("/message/queue")
public class RabbitMQController {
	
	private static final Logger logger = LoggerFactory.getLogger(RabbitMQController.class);

	@Autowired
	private RefundRabbitMQService rRMQService;
	
//	@Autowired
//	private ActivityRabbitMQService aRMQService;
	
	@RequestMapping("refund/send")
	public void sendOrderMessage(@RequestParam(value = "refundMessage",required = true)Map<String, List> refundMessage) {
		logger.info("发送到refund消息队列的信息：", refundMessage);
		rRMQService.sendRefundMessageToQueue(refundMessage);
	}
	
	@RequestMapping("refund/receive")
	public Collection<List> receiveRefundMessage(@RequestParam(value = "refundMessage",required = false)Map<String, List> refundMessage,
			@RequestBody List refundPayid) {
		return rRMQService.receiveRefundMessageFromQueue(refundMessage);
		
	}
	/*
	 * @RequestMapping("activity/send") public void
	 * sendActMessage(@RequestParam(value = "actMessage",required = true)Object
	 * actMessage) { logger.info("发送到activity消息队列的信息：", actMessage);
	 * aRMQService.sendActMessageToQueue(actMessage); }
	 * 
	 * @RequestMapping("activity/receive") public Object
	 * receiveActMessage(@RequestParam(value = "actMessage",required = false)Object
	 * actMessage,
	 * 
	 * @RequestBody Object message) { return
	 * aRMQService.receiveActMessageFromQueue(actMessage); }
	 */
	
}
