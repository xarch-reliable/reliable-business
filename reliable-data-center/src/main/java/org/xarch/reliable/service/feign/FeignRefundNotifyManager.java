package org.xarch.reliable.service.feign;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.xarch.reliable.controller.hystrix.FeignNotifyHystrix;

@FeignClient(name = "reliable-refund-notify-manager",fallback = FeignNotifyHystrix.class)
public interface FeignRefundNotifyManager {
	
	@PostMapping("/refund/notify/info/set")
	public Map<String, Object> setRefundNotify(@RequestParam(value = "out_refund_no", required = true) String out_refund_no, @RequestBody Map<String, Object> data);

	@PostMapping("/refund/notify/info/get")
	public Map<String, Object> getRefundNotify(@RequestParam(value = "out_refund_no", required = true) String out_refund_no);

}
