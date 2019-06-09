package org.xarch.reliable.controller.hystrix;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.xarch.reliable.service.feign.FeignActidManager;
import org.xarch.reliable.service.feign.FeignOpenidManager;

@Service
public class FeignOmAmHystrix implements FeignActidManager, FeignOpenidManager {

	@Override
	public Map<String, String> addOM(String openid, String actid) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("error_msg", "[FeignOpenidManager]获取addOM失败");
		map.put("openid", openid);
		map.put("actid", actid);
		return map;
	}

	@Override
	public Map<String, String> checkOM(String openid, String actid) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("error_msg", "[FeignOpenidManager]获取checkOM失败");
		map.put("openid", openid);
		map.put("actid", actid);
		return map;
	}

	@Override
	public Map<String, String> getOM(String openid) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("error_msg", "[FeignOpenidManager]获取getOM失败");
		map.put("openid", openid);
		return map;
	}

	@Override
	public Map<String, String> addAM(String actid, String openid) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("error_msg", "[FeignActidManager]获取addAM失败");
		map.put("openid", openid);
		map.put("actid", actid);
		return map;
	}

	@Override
	public Map<String, String> checkAM(String actid, String openid) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("error_msg", "[FeignActidManager]获取checkAM失败");
		map.put("openid", openid);
		map.put("actid", actid);
		return map;
	}

	@Override
	public Map<String, String> getAM(String actid) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("error_msg", "[FeignActidManager]获取getAM失败");
		map.put("actid", actid);
		return map;
	}

}
