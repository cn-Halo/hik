package com.hik.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Data;

/**
 * Created by yzm on 2019/8/19.
 * 考勤
 */
@Data
@Entity
@Table(name = "考勤", indexes = {
    @Index(name = "index_personName", columnList = "personName"),
    @Index(name = "index_eventTime", columnList = "eventTime")
}
)
public class Attendance implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    private String eventId;
    @Column(length = 1000)
    private String eventName;
    private String eventTime;
    @Temporal(TemporalType.TIMESTAMP)
    private Date eventTime2;
    private String personId;
    private String cardNo;
    @Column(length = 1000)
    private String personName;
    @Column(length = 1000)
    private String orgIndexCode;  //人员类型
    @Column(length = 1000)
    private String doorName;
    @Column(length = 1000)
    private String doorIndexCode;
    @Column(length = 1000)
    private String doorRegionIndexCode;
    @Column(length = 1000)
    private String picUri;
    @Column(length = 1000)
    private String svrIndexCode;
    @Column(length = 1000)
    private String eventType;
    @Column(length = 1000)
    private String inAndOutType;
    @Column(length = 1000)
    private String kqlx;
    @Column(length = 1000)
    private String danwei;
    @Column(length = 1000)
    private String attResult; //考勤结果
}
