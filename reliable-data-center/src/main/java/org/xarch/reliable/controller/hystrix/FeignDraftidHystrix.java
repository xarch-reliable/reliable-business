package org.xarch.reliable.controller.hystrix;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.xarch.reliable.service.feign.FeignDraftidManager;

@Service
public class FeignDraftidHystrix implements FeignDraftidManager {

	@Override
	public Map<String, Object> setDraftidinfo(String openid, String draftid,@RequestBody Map<String, Object> draftdata) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("error_msg", "[FeignDraftidManager]发起setDraftidinfo请求失败");
		return map;
	}
	
	@Override
	public Set getDraftidinfo(String openid){
		
		/*
		 * Map<String, Object> map = new HashMap<String, Object>(); map.put("error_msg",
		 * "[FeignDraftidManager]发起getDraftidinfo请求失败"); return map;
		 */
		Set body = new HashSet();
	}

}
