package org.xarch.reliable.controller.hystrix;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.xarch.reliable.service.feign.FeignActidManager;
import org.xarch.reliable.service.feign.FeignOpenidManager;

@Service
public class FeignOmAmHystrix implements FeignActidManager, FeignOpenidManager {

	@Override
	public Map<String, Object> addOM(String openid, String actid) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("error_msg", "[FeignOpenidManager]获取addOM失败");
		map.put("openid", openid);
		map.put("actid", actid);
		return map;
	}

	@Override
	public Map<String, Object> checkOM(String openid, String actid) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("error_msg", "[FeignOpenidManager]获取checkOM失败");
		map.put("openid", openid);
		map.put("actid", actid);
		return map;
	}

	@Override
	public Map<String, Object> getOM(String openid) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("error_msg", "[FeignOpenidManager]获取getOM失败");
		map.put("openid", openid);
		return map;
	}

	@Override
	public Map<String, Object> addAM(String actid, String openid) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("error_msg", "[FeignActidManager]获取addAM失败");
		map.put("openid", openid);
		map.put("actid", actid);
		return map;
	}

	@Override
	public Map<String, Object> checkAM(String actid, String openid) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("error_msg", "[FeignActidManager]获取checkAM失败");
		map.put("openid", openid);
		map.put("actid", actid);
		return map;
	}

	@Override
	public Map<String, Object> getAM(String actid) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("error_msg", "[FeignActidManager]获取getAM失败");
		map.put("actid", actid);
		return map;
	}

}
