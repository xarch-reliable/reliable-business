package org.xarch.reliable.service.bus.dispatcher;

import java.util.Map;

import org.xarch.reliable.config.event.ReliableMsgType;

public abstract class ActionMnager {

	@SuppressWarnings("unchecked")
	protected Map<String, Object> dispatch(String openid, Map<String, Object> bodyMap,Map<String, Object> responseMap) {
		if (bodyMap.get("xraction") == null) {
			responseMap.put("error_msg", "业务识别失败");
			return responseMap;
		}
		ReliableMsgType msgType = ReliableMsgType.valueOf((String) bodyMap.get("xraction"));
		switch (msgType) {
		case create:
			responseMap.put("body", onCrete(openid, (Map<String, String>) bodyMap.get("data")));
			responseMap.put("xraction", msgType);
			break;
		case userinfo:
			responseMap.put("body", onUserinfo((Map<String, String>) bodyMap.get("data")));
			break;
		case share:
			responseMap.put("body", onShare());
			break;
		case join:
		case finish:
		case signin:
		default:
			responseMap.put("body", onDefault());
			break;
		}
		return responseMap;
	};

	protected abstract Map<String, Object> onCrete(String openid, Map<String, String> actInfo);

	protected abstract Map<String, Object> onUserinfo(Map<String, String> actInfo);

	protected abstract Map<String, Object> onShare();

	protected String onDefault() {
		return "功能暂未开放，系统优化中...敬请期待!";
	};
}
