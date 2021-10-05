package com.org.eventLogger.entity;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.math.BigInteger;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class EventDetails implements Serializable {

    @JacksonXmlProperty(localName = "id")
    @ApiModelProperty(value = "the unique event identifier")
    @NotNull(message = "id cannto be blank")
    private String id;

    @JacksonXmlProperty(localName = "state")
    @ApiModelProperty(value = "whether the event was started or finished (can have values \"STARTED\" or \"FINISHED\"")
    @NotNull(message = "state cannot be blank")
    private String state;

    @JacksonXmlProperty(localName = "type")
    @ApiModelProperty(value = " type of log")
    private String type;

    @JacksonXmlProperty(localName = "host")
    @ApiModelProperty(value = "host Name")
    private String host;

    @JacksonXmlProperty(localName = "timestamp")
    @ApiModelProperty(value = " the timestamp of the event in milliseconds")
    @NotNull(message = "timestamp cannot be blank")
    private BigInteger timestamp;

    @Override
    public boolean equals(Object obj){
        boolean returnBoolean = false;

        if(obj==null) returnBoolean =  false;
        if(obj==this) returnBoolean = true;

        return returnBoolean;
    }

}
