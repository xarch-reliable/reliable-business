package org.xarch.reliable.controller.hystrix;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.xarch.reliable.service.feign.FeignActInfoManager;
import org.xarch.reliable.service.feign.FeignActidManager;
import org.xarch.reliable.service.feign.FeignOpenidManager;
import org.xarch.reliable.service.feign.FeignPayManager;
import org.xarch.reliable.service.feign.FeignPayidManager;
import org.xarch.reliable.utils.BaseResultTools;

@Service
public class FeignPayHystrix
		implements FeignPayidManager, FeignPayManager, FeignOpenidManager, FeignActidManager, FeignActInfoManager {

	@Override
	public String getPayid2Map(@RequestParam String actid, @RequestParam String openid) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("error_msg", "[FeignPayid]获取payipMap失败");
		map.put("openid", openid);
		map.put("actid", actid);
		return BaseResultTools.JsonObjectToStr(map);
	};

	@Override
	public String getMap(@RequestParam String actid) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("error_msg", "[FeignPayid]获取payMap失败");
		map.put("actid", actid);
		return BaseResultTools.JsonObjectToStr(map);
	}

	@Override
	public String getPayMpOrder(@RequestParam String openid, @RequestParam String payid) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("error_msg", "[FeignPay]发起支付请求失败");
		map.put("openid", openid);
		map.put("payid", payid);
		return BaseResultTools.JsonObjectToStr(map);
	}

	@Override
	public String getPayRefund(@RequestParam String payid) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("error_msg", "[FeignPay]发起退款请求失败");
		map.put("payid", payid);
		return BaseResultTools.JsonObjectToStr(map);
	}

	@Override
	public String addOM(@RequestParam String openid, @RequestParam String actid) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("error_msg", "[FeignOpenidManager]发起addOM请求失败");
		map.put("openid", openid);
		map.put("actid", actid);
		return BaseResultTools.JsonObjectToStr(map);
	}

	@Override
	public String checkOM(@RequestParam String openid, @RequestParam String actid) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("error_msg", "[FeignOpenidManager]发起checkOM请求失败");
		map.put("openid", openid);
		map.put("actid", actid);
		return BaseResultTools.JsonObjectToStr(map);
	}

	@Override
	public String getOM(@RequestParam String openid) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("error_msg", "[FeignOpenidManager]发起getOM请求失败");
		map.put("openid", openid);
		return BaseResultTools.JsonObjectToStr(map);
	}

	@Override
	public String addAM(@RequestParam String actid, @RequestParam String openid) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("error_msg", "[FeignActidManager]发起addAM请求失败");
		map.put("actid", actid);
		map.put("openid", openid);
		return BaseResultTools.JsonObjectToStr(map);
	}

	@Override
	public String checkAM(@RequestParam String actid, @RequestParam String openid) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("error_msg", "[FeignActidManager]发起checkAM请求失败");
		map.put("actid", actid);
		map.put("openid", openid);
		return BaseResultTools.JsonObjectToStr(map);
	}

	@Override
	public String getAM(@RequestParam String actid) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("error_msg", "[FeignActidManager]发起getAM请求失败");
		map.put("actid", actid);
		return BaseResultTools.JsonObjectToStr(map);
	}

	@Override
	public String setActid2ActInfo(String reliableActivityInfo) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("error_msg", "[FeignActInfoManager]发起setActid2ActInfo请求失败");
		map.put("body", reliableActivityInfo);
		return BaseResultTools.JsonObjectToStr(map);
	}

	@Override
	public String getAllActInfo() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("error_msg", "[FeignActInfoManager]发起getAllActInfo请求失败");
		return BaseResultTools.JsonObjectToStr(map);
	}

	@Override
	public String getActid2ActInfo(String actid) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("error_msg", "[FeignActInfoManager]发起getActid2ActInfo请求失败");
		map.put("actid", actid);
		return BaseResultTools.JsonObjectToStr(map);
	};
}
