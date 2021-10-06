package com.org.eventLogger.repository.entity;


import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = EventLogTableEntity.TABLE_NAME)
@Getter
@NoArgsConstructor
public class EventLogTableEntity implements Serializable {
    public static final String TABLE_NAME = "TEVENTLOG";

    @Id
    @Column(name = "id")
    private String id;


    @Column(name = "duration")
    private Integer eventDuration;

    @Column(name = "type")
    private String type;

    @Column(name = "host")
    private String host;

    @Column(name = "alert")
    private boolean alert;

    @Override
    public boolean equals(Object obj){
        return obj ==null? false: obj ==this?true:false;
    }
}
