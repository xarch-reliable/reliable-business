package org.xarch.reliable.service.thread;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.xarch.reliable.model.domain.reliable.ReliableActivityInfo;
import org.xarch.reliable.model.repository.ReliableActivityInfoRepository;

@Component
public class ThreadPool {

	@Autowired
	private ReliableActivityInfoRepository activityInfoRepository;

	@Async("asyncExecutor")
	public void StorageActInfoThread(ReliableActivityInfo reliableActivityInfo) {
		activityInfoRepository.save(reliableActivityInfo);
	}

}
