package org.xarch.reliable.service;

import java.util.Map;

import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

public interface OpenidCacheServer {

	@CachePut(cacheNames = "Openid", key = "#openid")
	public Map<String, String> setOpenidMap(String openid, Map<String, String> map);

	@Cacheable(cacheNames = "Openid", key = "#openid")
	public Map<String, String> getOpenidMap(String openid, Map<String, String> map);
	
}
