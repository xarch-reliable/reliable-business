package org.xarch.reliable.service;

import java.util.List;

import org.xarch.reliable.model.domain.reliable.ReliableActivityInfo;

public interface ReliableActInfoServer {
	
	public boolean createActInfo(ReliableActivityInfo reliableActivityInfo);
	
	public boolean fininshActInfo(ReliableActivityInfo reliableActivityInfo);

	public ReliableActivityInfo getActInfo(String actid);
	
	public List<ReliableActivityInfo> getAllActInfo();
}
