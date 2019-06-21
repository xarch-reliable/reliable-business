package org.xarch.reliable.service.bus.dispatcher;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xarch.reliable.config.event.ReliableMsgType;

public abstract class ActionMnager {

	private static final Logger logger = LoggerFactory.getLogger(ActionMnager.class);
	
	@SuppressWarnings("unchecked")
	protected Map<String, Object> dispatch(String openid, Map<String, Object> bodyMap,
			Map<String, Object> responseMap) {
		String xraction = (String)bodyMap.get("xraction");
		logger.info("ActionMnager::dispatch() : xraction = " + xraction);
		if (xraction == null) {
			responseMap.put("error_msg", "业务识别失败");
			return responseMap;
		}
		ReliableMsgType msgType = ReliableMsgType.valueOf(xraction);
		switch (msgType) {
		case create:
			responseMap.put("body", onCrete(openid, (Map<String, String>) bodyMap.get("data")));
			break;
		case share:
			responseMap.put("body", onShare((Map<String, String>) bodyMap.get("data")));
			break;
		case actinfo:
			responseMap.put("body", onActInfo((Map<String, String>) bodyMap.get("data")));
			break;
		case allactinfo:
			responseMap.put("body", onAllActInfo(openid));
			break;
		case join:
			responseMap.put("body", onJoin(openid, (Map<String, String>) bodyMap.get("data")));
			break;
		case partuserinfo:
			responseMap.put("body", onPartUserInfo((Map<String, String>) bodyMap.get("data")));
			break;
		case signin:
		case finish:
		default:
			responseMap.put("body", onDefault());
			break;
		}
		responseMap.put("xraction", msgType);
		return responseMap;
	};

	protected abstract List<Map<String, String>> onPartUserInfo(Map<String, String> map);

	protected abstract Map<String, Object> onCrete(String openid, Map<String, String> data);

	protected abstract Map<String, Object> onUserInfo(Map<String, String> data);

	protected abstract Map<String, Object> onShare(Map<String, String> data);

	protected abstract Map<String, Object> onActInfo(Map<String, String> data);

	protected abstract List<Map<String, Object>> onAllActInfo(String openid);

	protected abstract Map<String, Object> onJoin(String openid, Map<String, String> data);

	protected String onDefault() {
		return "功能暂未开放，系统优化中...敬请期待!";
	};
}
