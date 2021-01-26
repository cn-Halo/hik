package com.hik.service;

import com.hik.util.DateUtil;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by yzm on 2017/9/14.
 * 自动任务
 */
@Component
@Slf4j
public class SchedulerService {

    @Autowired
    AttendanceService attendanceService;

    @Autowired
    FaceAttendanceService faceAttendanceService;

    //ISO8601 时间格式
    private static String pattern = "yyyy-MM-dd'T'HH:mm:ssXXX";

    /**
     * 每一分钟执行一次
     */
    @Scheduled(cron = "0 0/1 * * * ?")
    public void testTasks() {
        log.info("自动任务开始执行...");

        String startDate = DateUtil.addNowMiniute(DateUtil.ISO8601_FORMAT, -1);
        String endDate = DateUtil.dateToString(new Date(), DateUtil.ISO8601_FORMAT);
        log.info("【自动任务开始调用海康接口】 startDate = {} , endDate = {}", startDate, endDate);
        try {
            attendanceService.call(1, 300, startDate, endDate);
        } catch (Exception e) {
            log.error("【自动任务调用海康接口异常】startDate = {} , endDate = {}", startDate, endDate, e);
        }


//        String startTime = DateUtil.addNowMiniute(DateUtil.FULL_FORMAT, -1);
//        String endTime = DateUtil.dateToString(new Date(), DateUtil.FULL_FORMAT);
//        log.info("【自动任务开始调用人脸考勤接口】 startTime = {} , endTime = {}", startTime, endTime);
//        try {
//            faceAttendanceService.queryWithTime(startTime, endTime);
//        } catch (Exception e) {
//            log.error("【自动任务调用人脸考勤接口异常】 startTime = {} , endTime = {}", startTime, endTime, e);
//        }

    }

    //2019-08-01T17:30:08+08:00
    public static void main(String[] args) throws Exception {

        // DateFormat sdf = new SimpleDateFormat(pattern);
        // String endDate = sdf.format(new Date());
        //
        // System.out.println(endDate);
        //
        // // String startDate  = addNowMiniute(pattern,-10);
        // // System.out.println(startDate);
        //
        // SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
        // String s = sdf2.format(new Date());
        // System.out.println(s);
        //
        // String p = "yyyy-MM-dd HH:mm:ss";
        // SimpleDateFormat sdf3 = new SimpleDateFormat(pattern);
        // Date date = sdf3.parse(s);
        // SimpleDateFormat sdf4 = new SimpleDateFormat(p);
        // String s4 = sdf4.format(date);
        // System.out.println(s4);
        // Date d = DateUtil
        //     .addMonth(DateUtil.stringToDate("2016-11-01 00:00:00", DateUtil.FULL_FORMAT),
        //         String.valueOf(1));
        // System.out.println(DateUtil.dateToString(d, DateUtil.FULL_FORMAT));

        /**
         *

         String originDate = "2016-01-01 00:00:00";
         Date start = DateUtil.stringToDate(originDate, DateUtil.FULL_FORMAT);
         Date nowDate = new Date();
         int monthDiff = DateUtil.getMonthDiff(start, nowDate);
         System.out.println(monthDiff);
         String startDate = "";
         String endDate = "";
         for (int i = 1; i <= monthDiff; i++) {
         if (i == 1) {
         startDate = originDate;
         } else {
         startDate = endDate;
         }
         Date startTime = DateUtil.stringToDate(startDate, DateUtil.FULL_FORMAT);

         // Date endTime = DateUtil.addMonth(startTime, String.valueOf(1));

         Date endTime =  DateUtil.subMonth(startDate,1);

         System.out.println(DateUtil.dateToString(startTime, DateUtil.FULL_FORMAT));
         System.out.println(DateUtil.dateToString(endTime, DateUtil.FULL_FORMAT));
         System.out.println("---------------------------------------------------");

         endDate = DateUtil.dateToString(endTime, DateUtil.FULL_FORMAT);

         String isoStartTime = DateUtil.dateToString(startTime, DateUtil.ISO8601_FORMAT);
         String isoEndTime = DateUtil.dateToString(endTime, DateUtil.ISO8601_FORMAT);
         }

         */
        // System.out.println(subMonth("2016-11-01 00:00:00"));

        int pageNo = 1;
        int pageSize = 1000;
        int totalPage = 1;
        for (int j = pageNo; j <= totalPage; j++) {
            // call(pageNo, pageSize, isoStartTime, isoEndTime);
            System.out.println(j + " || " + pageNo);
        }

        /**
         *
         1 || 1
         1 || 2
         1 || 3
         1 || 4
         1 || 5
         1 || 6
         .....
         */
    }


}
