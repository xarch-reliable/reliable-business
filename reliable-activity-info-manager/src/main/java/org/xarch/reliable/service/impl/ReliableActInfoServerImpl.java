package org.xarch.reliable.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xarch.reliable.model.domain.reliable.ReliableActivityInfo;
import org.xarch.reliable.model.repository.ReliableActivityInfoRepository;
import org.xarch.reliable.service.ReliableActInfoServer;
import org.xarch.reliable.service.thread.ThreadPool;

@Service
public class ReliableActInfoServerImpl implements ReliableActInfoServer {

	@Autowired
	private ReliableActivityInfoRepository activityInfoRepository;

	@Autowired
	private ThreadPool threadPool;

	@Override
	public ReliableActivityInfo getActInfo(String actid) {
		return activityInfoRepository.findByActid(actid);
	}

	@Override
	public List<ReliableActivityInfo> getAllActInfo() {
		return activityInfoRepository.findAll();
	}

	@Override
	public boolean setActInfo(ReliableActivityInfo reliableActivityInfo) {
		ReliableActivityInfo existing = activityInfoRepository.findByActid(reliableActivityInfo.getActid());
		if (existing != null) {
			return false;
		}
		threadPool.StorageActInfoThread(reliableActivityInfo);
		return true;
	}

}
