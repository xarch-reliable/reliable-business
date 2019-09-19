package xarch.reliable.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import xarch.reliable.service.RefundInfoServer;
import xarch.reliable.util.BaseResultTools;




@RestController
@RequestMapping(value ="/refund/request/info")
public class RedisController {
	private static final Logger logger = LoggerFactory.getLogger(RedisController.class);
	
	@Autowired
	private RefundInfoServer refundInfoServer;
	@RequestMapping("/set")
	public String setMap(@RequestParam(value = "payid", required = true) String payid,
			@RequestBody Map<String, Object> refunddata) {
		
		logger.info("RedisController() :: setMap : refunddata="+BaseResultTools.JsonObjectToStr(refunddata));
		return refundInfoServer.setRefundInfo(payid, refunddata);
		
	}
	@RequestMapping("/get")
	public Map<String, Object> getMap(@RequestParam(value = "payid", required = true) String payid) {
		logger.info("RedisController() :: getMap : payid="+payid);
		return refundInfoServer.getRefundInfo(payid);
	}
}
