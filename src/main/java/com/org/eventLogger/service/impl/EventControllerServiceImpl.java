package com.org.eventLogger.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.org.eventLogger.model.EventDetail;
import com.org.eventLogger.model.EventLog;
import com.org.eventLogger.repository.TeventLogRepository;
import com.org.eventLogger.repository.entity.EventLogTableEntity;
import com.org.eventLogger.service.EventControllerService;
import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.*;

@Service
public class EventControllerServiceImpl implements EventControllerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(EventControllerServiceImpl.class);
    @Autowired
    Mapper mapper;

    @Autowired
    TeventLogRepository teventLogRepository;

    @Override
    public void processFile(MultipartFile file) throws IOException {

        parseFile(file);
        LOGGER.info("Finished processing the file");

    }

    @Override
    public Optional<EventLogTableEntity> getEvent(String id) {
        LOGGER.info("started getting the events");
        return teventLogRepository.findById(id);
    }

    private void parseFile(MultipartFile file) throws IOException {

        LOGGER.info("Started parsing the file");
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, EventLog> eventDetailsMap = new HashMap<>();

        InputStream fileInputStream = new BufferedInputStream(file.getInputStream());
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));

            String strLine;
            while ((strLine = bufferedReader.readLine()) != null)   {
                EventDetail eventDetail = objectMapper.readValue(strLine, EventDetail.class);
                EventLog eventLog = new EventLog();
                mapper.map(eventDetail, eventLog);
                if(!eventDetailsMap.containsKey(eventLog.getId())){
                    eventLog.setStartTimestamp(eventDetail.getTimestamp());
                    eventDetailsMap.put( eventLog.getId(), eventLog);
                }else{
                    eventLog.setFinishTimestamp(eventDetail.getTimestamp());
                    eventLog.setStartTimestamp(eventDetailsMap.get(eventLog.getId()).getStartTimestamp());
                    eventLog.setEventDuration(Math.abs(eventLog.getFinishTimestamp().subtract(eventLog.getStartTimestamp()).intValue()));
                    eventLog.setAlert(eventLog.getEventDuration()>4?true:false);
                    eventDetailsMap.put( eventLog.getId(), eventLog);
                }
                LOGGER.debug("Inserting into the database" + eventLog.getId());
                EventLogTableEntity eventLogTableEntity = mapper.map(eventLog, EventLogTableEntity.class);
                teventLogRepository.save(eventLogTableEntity);

            }
            fileInputStream.close();
    }
}
