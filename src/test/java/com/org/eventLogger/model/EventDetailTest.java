package com.org.eventLogger.model;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.org.eventLogger.model.EventDetail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import java.io.*;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class EventDetailTest {


    private MockMultipartFile file;
    private EventDetail eventDetail;

    @BeforeEach
    public void setUp() throws IOException {

        byte[] json = ("{\"id\":\"scsmbstgra\", \"state\":\"STARTED\", \"type\":\"APPLICATION_LOG\", \"host\":\"12345\", \"timestamp\":1491377495212}"
                + "\n")
                .getBytes(StandardCharsets.UTF_8);

        this.file = new
                MockMultipartFile("file",
                "sample.txt",
                "text/plain", json);

        InputStream fileInputStream = new BufferedInputStream(file.getInputStream());
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));

        String strLine;
        while ((strLine = bufferedReader.readLine()) != null) {
            this.eventDetail = objectMapper.readValue(strLine, EventDetail.class);
        }

    }


    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void  testModel() throws IOException {

        assertEquals(this.eventDetail.getHost(), "12345");
        assertEquals(this.eventDetail.getId(), "scsmbstgra");
        assertEquals(this.eventDetail.getState(), "STARTED");
        assertEquals(this.eventDetail.getType(), "APPLICATION_LOG");
    }
}