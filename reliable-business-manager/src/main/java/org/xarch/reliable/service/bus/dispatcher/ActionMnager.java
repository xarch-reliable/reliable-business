package org.xarch.reliable.service.bus.dispatcher;

import java.util.HashMap;
import java.util.Map;

import org.xarch.reliable.config.event.ReliableMsgType;
import org.xarch.reliable.utils.BaseResultTools;

public abstract class ActionMnager {

	@SuppressWarnings("unchecked")
	protected String dispatch(String openid, Map<String, Object> bodyMap) {
		if (bodyMap.get("xraction") == null) {
			return "业务识别失败";
		}
		ReliableMsgType msgType = ReliableMsgType.valueOf((String) bodyMap.get("xraction"));
		Map<String, Object> map = new HashMap<String, Object>();
		switch (msgType) {
		case create:
			map.put("body", onCrete((Map<String, String>) bodyMap.get("data")));
			map.put("date", bodyMap.get("data"));
			break;
		case userinfo:
			map.put("body", onUserinfo((Map<String, String>) bodyMap.get("data")));
			break;
		case share:
			onShare();
			break;
		case join:
		case finish:
		case signin:
		default:
			map.put("body", onDefault());
			break;
		}
		return BaseResultTools.JsonObjectToStr(map);
	};

	protected abstract String onCrete(Map<String, String> actInfo);

	protected abstract String onUserinfo(Map<String, String> actInfo);
	
	protected abstract String onShare();

	protected String onDefault() {
		return "功能暂未开放，系统优化中...敬请期待!";
	};
}
