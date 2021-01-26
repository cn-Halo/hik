package com.hik.exception;


import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Set;

@ControllerAdvice
public class GlobalControllerExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(GlobalControllerExceptionHandler.class);

    // 异常处理方法：
    // 根据特定的异常返回指定的 HTTP 状态码
    @ResponseStatus(value = HttpStatus.BAD_REQUEST) // 400
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public JSONObject handleValidationException(ConstraintViolationException ex) {
        Set<ConstraintViolation<?>> errors = ex.getConstraintViolations();
        StringBuilder strBuilder = new StringBuilder();
        for (ConstraintViolation<?> violation : errors) {
            strBuilder.append(violation.getMessage() + "\n");
        }

        JSONObject resp = new JSONObject();
        resp.put("success", false);
        resp.put("message", "请求错误: "+strBuilder.toString());

        return resp;
    }

    // 通用异常的处理，返回500
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR) // 500
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public JSONObject handleException(Exception ex) {
        StringWriter sw = null;
        PrintWriter pw = null;
        try {
            sw = new StringWriter();
            pw = new PrintWriter(sw);
            // 将出错的栈信息输出到printWriter中
            ex.printStackTrace(pw);
            pw.flush();
            sw.flush();
        } finally {
            if (sw != null) {
                try {
                    sw.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (pw != null) {
                pw.close();
            }
        }

        logger.error("统一异常处理: {} {} ", ex.getMessage(), sw.toString());
        JSONObject resp = new JSONObject();
        resp.put("success", false);

        if(ex instanceof FrontShowException || ex instanceof PayException) {
            resp.put("message", ex.getMessage());
        } else {
            resp.put("message", "服务器异常");
        }

        return resp;
    }
}