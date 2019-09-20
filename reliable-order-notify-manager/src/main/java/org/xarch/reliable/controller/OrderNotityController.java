package org.xarch.reliable.controller;

import java.util.Map;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.xarch.reliable.service.OrderNotifyService;
import org.xarch.reliable.util.BaseResultTools;

/**
*
*  @Description  Controller for dispatching
*
*  @Author  Jim_Carrey
*
*  @Date  2019年9月19日
*/
public class OrderNotityController {
	
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(OrderNotityController.class);
	
	@Autowired
	private OrderNotifyService oNotifyService;
	
	@RequestMapping("/set")
	public Map<String, Object> setMap(@RequestParam(value = "out_trade_no",required = true) String out_trade_no,
			@RequestBody Map<String, Object> oNotifyData) {
		
		logger.info("OrderNotifyController() :: setMap : oNotifyData="+BaseResultTools.JsonObjectToStr(oNotifyData));
		return oNotifyService.setOrderNotify(out_trade_no, oNotifyData);
		
	}
	
	@RequestMapping("/get")
	public Map<String, Object> getMap(@RequestParam(value = "out_trade_no",required = true) String out_trade_no){
		logger.info("OrderNotifyController() :: getMap : out_trade_no="+out_trade_no);
		return oNotifyService.getOrderNotify(out_trade_no);
		
		}
}
