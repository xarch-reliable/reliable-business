package org.xarch.reliable.service.bus;

import java.util.HashMap;
import java.util.Map;

import org.xarch.reliable.service.bus.dispatcher.ActionMnager;
import org.xarch.reliable.utils.BaseResultTools;

public abstract class BsinessManager extends ActionMnager {

	/**
	 * 调用入口，进行数据接收，分发消息，得到响应
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map<String, Object> execute(String RequestStr) {
		Map request = BaseResultTools.fromJSON(RequestStr, Map.class);
		Map<String, Object> responseMap = new HashMap<String, Object>();
		if (request.get("openid") == null) {
			responseMap.put("error_msg", "身份认证失败");
			return responseMap;
		}
		if (request.get("error_msg") != null) {
			responseMap.put("error_msg", request.get("error_msg"));
			return request;
		}
		return dispatch((String) request.get("openid"), (Map<String, Object>) request.get("body"), responseMap);
	}
}
