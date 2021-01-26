package com.hik.controller;

import com.alibaba.fastjson.JSONObject;
import com.hik.service.AttendanceService;
import com.hik.service.HikService;
import com.hik.vo.ResponseVO;
import io.swagger.annotations.ApiOperation;
import java.util.List;

import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yzm on 2019/8/10.
 */
@RestController
@RequestMapping("/hik")
public class HikServiceController {

    @Autowired
    AttendanceService attendanceService;


    @ApiOperation(value = "查询列表")
    @GetMapping
    public ResponseVO query() {
        JSONObject jsonBody = new JSONObject();
        jsonBody.put("pageNo", 1);
        jsonBody.put("pageSize", 20);
        jsonBody.put("startTime", "2019-08-01T17:30:08+08:00");
        jsonBody.put("endTime", "2019-08-10T17:30:08+08:00");

        String result = HikService.door(jsonBody);
        System.out.println("--------------------------------------");
        System.out.println("【结果】：" + result);
        System.out.println("--------------------------------------");
        return ResponseVO.defaultResp(result);
    }

    @ApiOperation(value = "查询所有并保存")
    @GetMapping("/list")
    public ResponseVO queryAll() throws Exception {
        String originDate = "2016-01-01 00:00:00";
        attendanceService.queryAll(originDate);
        return ResponseVO.defaultResp();
    }

    @ApiOperation(value = "根据起始查询所有并保存")
    @GetMapping("/list2")
    public ResponseVO queryAllWithOriginDate(@ApiParam(name = "originDate", value = "起始时间，格式如：2016-01-01 00:00:00")  @RequestParam String originDate) throws Exception {
        attendanceService.queryAll(originDate);
        return ResponseVO.defaultResp();
    }


}

