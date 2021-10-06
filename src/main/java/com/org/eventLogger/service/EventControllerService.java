package com.org.eventLogger.service;

import com.org.eventLogger.repository.entity.EventLogTableEntity;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.Optional;


public interface EventControllerService {

    void processFile(MultipartFile file) throws IOException;
    Optional<EventLogTableEntity> getEvent(String id);

}
