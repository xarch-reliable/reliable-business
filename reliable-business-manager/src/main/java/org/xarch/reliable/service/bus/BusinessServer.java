package org.xarch.reliable.service.bus;

import org.springframework.stereotype.Service;

@Service
public class BusinessServer extends BsinessManager{

	@Override
	protected String onCrete() {
		// TODO Auto-generated method stub
		return "{test:Crete}";
	}

	@Override
	protected String onUserinfo() {
		// TODO Auto-generated method stub
		return "{test:Userinfo}";
	}

}
