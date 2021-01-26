package com.hik.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * 人脸考勤数据
 */
@Data
@Entity
@Table(name = "人脸", indexes = {
        @Index(name = "index_username", columnList = "username"),
        @Index(name = "index_idcard", columnList = "idcard")
}
)
public class FaceAttendance {

    @Id
    @GeneratedValue
    private Long id;
    private String faceId;
    private String datetime;
    private String valid_time_type;
    private String valid_start;
    private String valid_end;
    private String username;
    private String idcard;
    private String sex;
    private String nation;
    private String state;
    private String wgId;
    private String iccard;
    private String age;
    private String birthday;
    private String id_address;
    private String id_authority;
    private String id_valid_time;
    private String recg_mode;
    private String recg_type;

}
