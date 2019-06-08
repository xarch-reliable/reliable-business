package org.xarch.reliable.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.xarch.reliable.model.domain.reliable.ReliableActivityInfo;

public interface ReliableActivityInfoRepository extends JpaRepository<ReliableActivityInfo, Long> {

	ReliableActivityInfo findByActid(String actid);

}
