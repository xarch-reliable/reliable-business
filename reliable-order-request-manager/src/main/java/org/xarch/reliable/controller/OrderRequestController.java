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
	
	@RequestMapping("/info/set")
	public String setMap(@RequestParam(value = "out_trade_no",required = true) String out_trade_no,
			@RequestBody Map<String, Object> oRequestData) {
		
		logger.info("OrderRequestController() :: setMap : oRequestData="+BaseResultTools.JsonObjectToStr(oRequestData));
		return oRequestService.setOrderRequest(out_trade_no, oRequestData);
		
	}
	
	@RequestMapping("/info/get")
	public Map<String, Object> getMap(@RequestParam(value = "out_trade_no",required = true) String out_trade_no){
		logger.info("OrderRequestController() :: getMap : out_trade_no="+out_trade_no);
		return oRequestService.getOrderRequest(out_trade_no);
		
		}
	}
	
	

