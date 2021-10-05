package com.org.eventLogger.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigInteger;

@Getter
@NoArgsConstructor
public class EventDetail implements Serializable {

    private String id;
    private String state;
    private String type;
    private String host;
    private BigInteger timestamp;


    @Override
    public boolean equals(Object obj){
        return obj ==null? false: obj ==this?true:false;
    }


}
