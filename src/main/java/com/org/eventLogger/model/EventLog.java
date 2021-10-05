package com.org.eventLogger.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;

import java.math.BigInteger;

@Getter
@Setter
@NoArgsConstructor
public class EventLog {

    private String id;
    private Integer eventDuration;
    private String type;
    private String host;
    private BigInteger startTimestamp;
    private BigInteger finishTimestamp;
    private boolean alert;

    @Override
    public boolean equals(Object obj){
        return obj ==null? false: obj ==this?true:false;
    }
}
