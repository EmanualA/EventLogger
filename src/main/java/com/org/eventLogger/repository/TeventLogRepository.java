package com.org.eventLogger.repository;


import com.org.eventLogger.repository.entity.EventLogTableEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface TeventLogRepository extends JpaRepository<EventLogTableEntity, String> {

    Logger LOGGER = LoggerFactory.getLogger(TeventLogRepository.class);

//    EventLogTableEntity findByIdAndState(String id, String state);

}
