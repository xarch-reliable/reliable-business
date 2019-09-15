package org.xarch.reliable.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xarch.reliable.service.DataDealServer;

@RestController
@RequestMapping("/reliable/data")
public class DataCenterController {
	private static final Logger logger = LoggerFactory.getLogger(DataCenterController.class);

	@Autowired
	private DataDealServer dataServer;
	
	@RequestMapping("/get")
	public Map<String, Object> GetDataSupport(@RequestBody String request) {
		logger.info("DataCenterController::Support() : request = " + request);
		return dataServer.execute(request);
	}
	
}
