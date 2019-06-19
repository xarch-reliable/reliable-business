package org.xarch.reliable.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.xarch.reliable.service.bus.BusinessServer;

@RestController
public class BusinessController {
	private static final Logger logger = LoggerFactory.getLogger(BusinessController.class);

	@Autowired
	private BusinessServer businessServer;

	@PostMapping("/reliable/support")
	public Map<String, Object> Support(@RequestBody String request) {
		logger.info("BusinessController::Support() : request = " + request);
		return businessServer.execute(request);
	}
}
