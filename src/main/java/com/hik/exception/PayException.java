package com.hik.exception;

import com.hik.enums.ResultEnum;

/**
 * Created by yzm on 2017/8/24.
 */
public class PayException extends RuntimeException {

    private Integer code;

    public PayException(ResultEnum resultEnum) {
        super(resultEnum.getNote());
        this.code = resultEnum.getCode();
    }

    public PayException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public PayException(ResultEnum resultEnum, Throwable cause) {
        super(resultEnum.getNote(), cause);
        this.code = resultEnum.getCode();
    }

}
