package org.xarch.reliable.service;

import java.util.List;

import org.xarch.reliable.model.domain.reliable.ReliableActivityInfo;

public interface ReliableActInfoServer {
	
	public boolean setActInfo(ReliableActivityInfo reliableActivityInfo);

	public ReliableActivityInfo getActInfo(String actid);
	
	public List<ReliableActivityInfo> getAllActInfo();
}
