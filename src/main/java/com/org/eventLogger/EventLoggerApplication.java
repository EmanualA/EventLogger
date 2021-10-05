package com.org.eventLogger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;


@SpringBootApplication
public class EventLoggerApplication {

	public static void main(String[] args) {
		SpringApplication.run(EventLoggerApplication.class, args);
	}

}
