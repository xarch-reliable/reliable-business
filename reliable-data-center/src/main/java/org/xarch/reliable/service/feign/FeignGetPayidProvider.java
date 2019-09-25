package org.xarch.reliable.service.feign;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.xarch.reliable.controller.hystrix.FeignPayHystrix;

@FeignClient(name = "reliable-payid-provider",fallback = FeignPayHystrix.class)
public interface FeignGetPayidProvider {
	
	@GetMapping("/payid/get")
	public Map<String, Object> getPayid();
}
