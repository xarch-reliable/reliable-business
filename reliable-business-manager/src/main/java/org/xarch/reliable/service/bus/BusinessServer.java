package org.xarch.reliable.service.bus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xarch.reliable.service.feign.FeignActInfoManager;

@Service
public class BusinessServer extends BsinessManager{

	@Autowired
	private FeignActInfoManager feignActInfoManager;
	
	@Override
	protected String onCrete() {
		// TODO Auto-generated method stub
		return "testcreate";
	}

	@Override
	protected String onUserinfo() {
		// TODO Auto-generated method stub
		return "{test:Userinfo}";
	}

}
