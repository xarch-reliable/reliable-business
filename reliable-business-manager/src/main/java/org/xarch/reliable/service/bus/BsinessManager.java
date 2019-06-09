package org.xarch.reliable.service.bus;

import java.util.Map;

import org.xarch.reliable.service.bus.dispatcher.ActionMnager;
import org.xarch.reliable.utils.BaseResultTools;

public abstract class BsinessManager extends ActionMnager {

	/**
	 * 调用入口，进行数据接收，分发消息，得到响应
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String execute(String RequestStr) {
		Map request = BaseResultTools.fromJSON(RequestStr, Map.class);
		if (request.get("openid") == null) {
			return "身份认证失败";
		}
		if (request.get("error_msg") != null) {
			return (String) request.get("error_msg");
		}
		return dispatch((String) request.get("openid"), (Map<String, Object>) request.get("body"));
	}
}
