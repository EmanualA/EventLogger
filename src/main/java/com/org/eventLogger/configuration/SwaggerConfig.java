package com.org.eventLogger.configuration;

import com.org.eventLogger.EventLoggerApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;


@Configuration
public class SwaggerConfig{

    @Bean
    public Docket apiDocket(){
        return new Docket(DocumentationType.SWAGGER_2)
                    .directModelSubstitute(LocalDate.class, java.sql.Date.class)
                    .directModelSubstitute(LocalDateTime.class, java.util.Date.class)
                    .useDefaultResponseMessages(false)
                    .apiInfo(apiInfo())
                    .select()
                        .apis(RequestHandlerSelectors.basePackage(EventLoggerApplication.class.getPackage().getName()))
                        .paths(PathSelectors.any())
                .build();

    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("EventLogger")
                .description("Api to log events which take more than 4 milliseconds")
                .contact(new Contact("Emanual Alby", "", "emanual.alby@gmail.com"))
                .version("1.0")
                .build();
    }
}
