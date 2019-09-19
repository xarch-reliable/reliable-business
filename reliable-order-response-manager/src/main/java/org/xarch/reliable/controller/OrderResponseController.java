package org.xarch.reliable.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.xarch.reliable.service.OrderResponseService;
import org.xarch.reliable.util.BaseResultTools;

/**
*
*  @Description  Controller for dispatching
*
*  @Author  Jim_Carrey
*
*  @Date  2019年9月19日
*/
@RestController
@RequestMapping("/order/response")
public class OrderResponseController {
	
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(OrderResponseController.class);
	
	@Autowired
	private OrderResponseService oResponseService;
	
	@RequestMapping("/set")
	public String setMap(@RequestParam(value = "prepay_id",required = true) String prepay_id,
			@RequestBody Map<String, Object> oResponseData) {
		
		logger.info("OrderResponseController() :: setMap : oResponseData="+BaseResultTools.JsonObjectToStr(oResponseData));
		return oResponseService.setOrderResponse(prepay_id, oResponseData);
		
	}
	
	@RequestMapping("/get")
	public Map<String, Object> getMap(@RequestParam(value = "prepay_id",required = true) String prepay_id){
		logger.info("OrderResponseController() :: getMap : prepay_id="+prepay_id);
		return oResponseService.getOrderResponse(prepay_id);
		
		}

}
