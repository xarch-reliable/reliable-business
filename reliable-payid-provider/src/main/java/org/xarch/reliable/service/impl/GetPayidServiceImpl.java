package org.xarch.reliable.service.impl;
/**
*
*  @Description  类描述
*
*  @Author  Jim_Carrey
*
*  @Date  2019年9月19日
*/

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xarch.reliable.service.GetPayidService;
import org.xarch.reliable.util.RedisUtil;
@Service
public class GetPayidServiceImpl implements GetPayidService{
	
	@Autowired
    private RedisUtil redisUtil;

	@Override
	public Map<String, String> getPayid() {
		Map<String, String> resmap = new HashMap<String,String>();
		String outTradeNo;
		do {
			Random random = new Random();
			int rannum = (int) (random.nextDouble() * (99999 - 10000 + 1)) + 10000;// 获取5位随机数
			outTradeNo = String.valueOf(System.currentTimeMillis()) + rannum;
		}while(redisUtil.hasKey(outTradeNo));
		redisUtil.set(outTradeNo, outTradeNo, 60);
		resmap.put("payid", outTradeNo);
		return resmap;
	}

}
