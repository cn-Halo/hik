package com.hik.enums;

import lombok.Getter;

/**
 * Created by yzm on 2017/8/24.
 */
@Getter
public enum ResultEnum implements CodeEnum {
    CALL_FACE_INTERFACE_FAIL(1, "调用人脸接口失败"),


    ;

    private Integer code;
    private String note;

    ResultEnum(Integer code, String note) {
        this.code = code;
        this.note = note;
    }
}
