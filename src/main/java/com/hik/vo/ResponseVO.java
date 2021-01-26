package com.hik.vo;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

/**
 * Created by yzm on 2017/9/11.
 */
@Data
public class ResponseVO {

    private boolean success;
    private String message;
    private Object data;

    public ResponseVO(boolean success, String message, Object data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }
    public ResponseVO( Object data) {
        this.success = true;
        this.message = "";
        this.data = data;
    }

    public ResponseVO() {
    }


    public static ResponseVO defaultResp() {
        return new ResponseVO(true, "", null);
    }

    public static ResponseVO defaultResp(JSONObject data) {
        return new ResponseVO(true, "", data);
    }

    public static ResponseVO defaultResp(Object data) {
        return new ResponseVO(true, "", data);
    }

}
