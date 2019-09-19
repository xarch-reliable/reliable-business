package org.xarch.reliable.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.xarch.reliable.service.RefundInfoServer;
import org.xarch.reliable.util.BaseResultTools;



@RestController
@RequestMapping(value ="/refund/notify/info")
public class RedisController {

private static final Logger logger = LoggerFactory.getLogger(RedisController.class);
	
	@Autowired
	private RefundInfoServer refundInfoServer;
	@RequestMapping("/set")
	public String setMap(@RequestParam(value = "out_refund_no", required = true) String  out_refund_no ,
			@RequestBody Map<String, Object> notifydata) {
		
		logger.info("RedisController() :: setMap : refunddata="+BaseResultTools.JsonObjectToStr(notifydata));
		return refundInfoServer.setRefundInfo( out_refund_no , notifydata);
		
	}
	@RequestMapping("/get")
	public Map<String, Object> getMap(@RequestParam(value = "out_refund_no", required = true) String   out_refund_no) {
		logger.info("RedisController() :: getMap : out_refund_no="+out_refund_no);
		return refundInfoServer.getRefundInfo(out_refund_no);
	}
}
