package org.xarch.reliable.service.feign;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.xarch.reliable.controller.hystrix.FeignPayHystrix;

@FeignClient(name = "reliable-wechat-pay", fallback = FeignPayHystrix.class)
public interface FeignPayManager {

	@GetMapping("/wechat/pay/h5")
	public Map<String, Object> getPayMpOrder(@RequestBody Map<String, Object> data);

	@GetMapping("/wechat/pay/refund")
	public Map<String, Object> getPayRefund(@RequestParam(value = "payid", required = true) String payid);
}
