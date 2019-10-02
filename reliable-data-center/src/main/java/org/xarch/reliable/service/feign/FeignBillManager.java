package org.xarch.reliable.service.feign;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.xarch.reliable.controller.hystrix.FeignBillHystrix;


@FeignClient(name = "reliable-bill-manager",fallback = FeignBillHystrix.class)
public interface FeignBillManager {
	
	@PostMapping("/bill/info/setbill") 
	public Map<String, Object> setBillinfo(@RequestParam(value = "openid", required = true) String openid,
			@RequestBody Map<String, Object> billdata);
	
	@PostMapping("/bill/info/getbill") 
	public Map<String, Object> getBillinfo(@RequestParam(value = "openid", required = true) String openid);


}
