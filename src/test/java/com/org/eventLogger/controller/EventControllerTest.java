package com.org.eventLogger.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;




@SpringBootTest
@AutoConfigureMockMvc
public class EventControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void readFile() throws Exception {

        byte[] json = ("{\"id\":\"scsmbstgra\", \"state\":\"STARTED\", \"type\":\"APPLICATION_LOG\", \"host\":\"12345\", \"timestamp\":1491377495212}" +
                "\n"+ "{\"id\":\"scsmbstgra\", \"state\":\"FINISHED\", \"type\":\"APPLICATION_LOG\", \"host\":\"12345\", \"timestamp\":1491377495217}")
                .getBytes(StandardCharsets.UTF_8);

        MockMultipartFile file = new MockMultipartFile("file", "sample.txt", "text/plain", json);

//        mvc.perform(MockMvcRequestBuilders.fileUpload("/EventLogger/readFile", "file")
//                .file(testFile))
//                .andExpect(status().isOk());

        this.mvc.perform(MockMvcRequestBuilders
                        .multipart("/EventLogger/readFile")
                        .file(file)
                        .with(SecurityMockMvcRequestPostProcessors.user("demo").password("demo")))
                .andExpect(status().isOk());

    }

    @Test
    public void readPath() throws Exception {

        this.mvc.perform(post("/EventLogger/readPath")
                .param("path", "/SampleFile/log.txt")
                .with(SecurityMockMvcRequestPostProcessors.user("demo").password("demo")))
                .andExpect(status().isOk());
    }

    @Test
    public void getEvent() throws Exception {

        this.mvc.perform(get("/EventLogger/get")
                .param("id", "scsmbstgrc")
                .with(SecurityMockMvcRequestPostProcessors.user("demo").password("demo")))
                .andExpect(status().isOk());

    }

}