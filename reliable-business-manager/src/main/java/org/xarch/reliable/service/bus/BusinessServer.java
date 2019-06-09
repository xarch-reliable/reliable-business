package org.xarch.reliable.service.bus;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xarch.reliable.service.feign.FeignActInfoManager;
import org.xarch.reliable.utils.BaseResultTools;

@Service
public class BusinessServer extends BsinessManager{

	@Autowired
	private FeignActInfoManager feignActInfoManager;
	
	@Override
	protected String onCrete(Map<String, String> actInfo) {
		// TODO Auto-generated method stub
		return feignActInfoManager.setActid2ActInfo(BaseResultTools.JsonObjectToStr(actInfo));
	}

	@Override
	protected String onUserinfo(Map<String, String> actInfo) {
		// TODO Auto-generated method stub
		return feignActInfoManager.getActid2ActInfo(actInfo.get("actid"));
	}

	@Override
	protected String onShare() {
		// TODO Auto-generated method stub
		return feignActInfoManager.getAllActInfo();
	}

}
