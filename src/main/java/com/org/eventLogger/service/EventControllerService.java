package com.org.eventLogger.service;

import com.org.eventLogger.model.EventDetail;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;


public interface EventControllerService {

    void processFile(MultipartFile file) throws IOException;
//    List<EventDetail> parseFile(MultipartFile file) throws IOException;
//  yes
//  public List<EventDetail> processFile(File file);
}
