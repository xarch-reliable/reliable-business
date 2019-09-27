package org.xarch.reliable.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xarch.reliable.service.GetPayidService;

/**
*
*  @Description  Controller for dispatching
*
*  @Author  wancy
*
*  @Date  2019年9月19日
*/
@RestController
public class PayidProviderController {
	
	@Autowired
	private GetPayidService getPayidService;
	
	@RequestMapping("/payid/get")
	public Map<String, String> getPayId() {
		return getPayidService.getPayid();
	}
}
