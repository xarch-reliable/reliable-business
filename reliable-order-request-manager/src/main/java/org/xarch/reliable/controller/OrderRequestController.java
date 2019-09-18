package org.xarch.reliable.controller;

import java.util.Map;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.xarch.reliable.service.OrderRequestService;
import org.xarch.reliable.util.BaseResultTools;

/**
*
*  @Description  Controller for dispatching
*
*  @Author  Jim_Carrey
*
*  @Date  2019年9月18日
*/
@RestController
@RequestMapping("/order/request")
public class OrderRequestController {
	
	@SuppressWarnings("unused")
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(OrderRequestController.class);
	
	@Autowired
	private OrderRequestService oRequestService;
	
	@RequestMapping("/set")
	public String setMap(@RequestParam(value = "payid",required = true) String payid,
			@RequestBody Map<String, Object> oRequestData) {
		
		logger.info("OrderRequestController() :: setMap : oRequestData="+BaseResultTools.JsonObjectToStr(oRequestData));
		return oRequestService.setOrderRequest(payid, oRequestData);
		
	}
	
	@RequestMapping("/get")
	public Map<String, Object> getMap(@RequestParam(value = "payid",required = true) String payid){
		logger.info("OrderRequestController() :: getMap : payid="+payid);
		return oRequestService.getOrderRequest(payid);
		
		}
	}
	
	

