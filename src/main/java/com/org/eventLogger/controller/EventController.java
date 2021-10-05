package com.org.eventLogger.controller;

import com.org.eventLogger.model.EventLog;
import com.org.eventLogger.service.EventControllerService;
import io.swagger.annotations.Api;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Api
@RestController
@RequestMapping("/EventLogger")
public class EventController {
    private static final Logger LOGGER = LoggerFactory.getLogger(EventController.class);
    @Autowired
    EventControllerService eventControllerService;

    @PostMapping("/readFile")
    public String readFile(@RequestPart("file") MultipartFile file) throws IOException {
//        public String readFile(@RequestParam String file){
        LOGGER.info("Received the file and started parsing the file");
        eventControllerService.processFile(file);
        return "File processed Sucessfully";

    }


    @PostMapping("/readPath")
    public String readPath(@RequestParam String path) throws IOException {
//        public String readFile(@RequestParam String file){
        LOGGER.info("Received the file path and started parsing the file");
        File file = new File(path);
        FileInputStream input = new FileInputStream(file);
        MultipartFile multipartFile = new MockMultipartFile("file",
                file.getName(), "text/plain", IOUtils.toByteArray(input));
        eventControllerService.processFile(multipartFile);

        return "File processed Sucessfully";
    }

    @GetMapping("/EventLogger/get")
    public String getEvent(){
//        return getEventDetails(id);
        return "Emanual";
    }


}
