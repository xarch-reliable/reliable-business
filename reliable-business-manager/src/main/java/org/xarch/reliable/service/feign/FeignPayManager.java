package org.xarch.reliable.service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.xarch.reliable.controller.hystrix.FeignPayHystrix;

@FeignClient(name = "reliable-wechat-pay", fallback = FeignPayHystrix.class)
public interface FeignPayManager {

	@GetMapping("/wechat/pay/h5")
	public String getPayMpOrder(@RequestParam(value = "openid", required = true) String openid,
			@RequestParam(value = "payid", required = true) String payid);

	@GetMapping("/wechat/pay/refund")
	public String getPayRefund(@RequestParam(value = "payid", required = true) String payid);
}
