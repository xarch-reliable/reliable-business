package org.xarch.reliable.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.xarch.reliable.service.bus.BusinessServer;

@RestController
public class BusinessController {

	@Autowired
	private BusinessServer businessServer;

	@PostMapping("/reliable/support")
	public Map<String, Object> Support(@RequestBody String request) {
		return businessServer.execute(request);
	}
}
