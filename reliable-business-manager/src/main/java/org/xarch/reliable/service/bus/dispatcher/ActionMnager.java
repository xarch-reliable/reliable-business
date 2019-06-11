package org.xarch.reliable.service.bus.dispatcher;

import java.util.List;
import java.util.Map;

import org.xarch.reliable.config.event.ReliableMsgType;

public abstract class ActionMnager {

	@SuppressWarnings("unchecked")
	protected Map<String, Object> dispatch(String openid, Map<String, Object> bodyMap,
			Map<String, Object> responseMap) {
		if (bodyMap.get("xraction") == null) {
			responseMap.put("error_msg", "业务识别失败");
			return responseMap;
		}
		ReliableMsgType msgType = ReliableMsgType.valueOf((String) bodyMap.get("xraction"));
		switch (msgType) {
		case create:
			responseMap.put("body", onCrete(openid, (Map<String, String>) bodyMap.get("data")));
			break;
		case userinfo:
			responseMap.put("body", onUserInfo((Map<String, String>) bodyMap.get("data")));
			break;
		case share:
			responseMap.put("body", onShare());
			break;
		case actinfo:
			responseMap.put("body", onActInfo((Map<String, String>) bodyMap.get("data")));
			break;
		case allactinfo:
			responseMap.put("body", onAllActInfo(openid));
			break;
		case join:
		case finish:
		case signin:
		default:
			responseMap.put("body", onDefault());
			break;
		}
		responseMap.put("xraction", msgType);
		return responseMap;
	};

	protected abstract Map<String, Object> onCrete(String openid, Map<String, String> data);

	protected abstract Map<String, Object> onUserInfo(Map<String, String> data);

	protected abstract Map<String, Object> onShare();
	
	protected abstract Map<String, Object> onActInfo(Map<String, String> data);
	
	protected abstract List<Map<String,Object>> onAllActInfo(String openid);

	protected String onDefault() {
		return "功能暂未开放，系统优化中...敬请期待!";
	};
}
