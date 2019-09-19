package org.xarch.reliable.service.feign;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.xarch.reliable.controller.hystrix.FeignRefundHystrix;

@FeignClient(name = "reliable-refund-request-manager",fallback = FeignRefundHystrix.class)
public interface FeignRefundRequestManager {
	
	@PostMapping("/refund/request/info/set")
	public Map<String, Object> setRefundRequest(@RequestParam(value = "out_refund_no", required = true) String out_refund_no, @RequestBody Map<String, Object> data);

	@PostMapping("/refund/request/info/get")
	public Map<String, Object> getRefundRequest(@RequestParam(value = "out_refund_no", required = true) String out_refund_no);
}
