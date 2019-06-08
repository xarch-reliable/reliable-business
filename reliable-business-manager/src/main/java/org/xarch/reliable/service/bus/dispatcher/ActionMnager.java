package org.xarch.reliable.service.bus.dispatcher;

import java.util.HashMap;
import java.util.Map;

import org.xarch.reliable.config.event.ReliableMsgType;
import org.xarch.reliable.utils.BaseResultTools;

public abstract class ActionMnager {

	protected String dispatch(String openid, Map<String, String> bodyMap) {
		if (bodyMap.get("xraction") == null) {
			return "业务识别失败";
		}
		ReliableMsgType msgType = ReliableMsgType.valueOf(bodyMap.get("xraction"));
		Map<String, Object> map = new HashMap<String, Object>();
		switch (msgType) {
		case create:
			map.put("body", onCrete());
			map.put("date", bodyMap.get("data"));
			break;
		case userinfo:
			map.put("body", onUserinfo());
			break;
		case share:
		case join:
		case finish:
		case signin:
		default:
			map.put("body", onDefault());
			break;
		}
		return BaseResultTools.JsonObjectToStr(map);
	};

	protected abstract String onCrete();

	protected abstract String onUserinfo();

	protected String onDefault() {
		return "功能暂未开放，系统优化中...敬请期待!";
	};
}
