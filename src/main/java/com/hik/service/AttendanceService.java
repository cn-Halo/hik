package com.hik.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hik.dao.AttendanceDao;
import com.hik.entity.Attendance;
import com.hik.util.DateUtil;
import com.hik.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by yzm on 2019/8/19.
 */
@Service
@Slf4j
public class AttendanceService {

    @Autowired
    AttendanceDao attendanceDao;

    // @Value("${danwei}")
    // private String danwei;
    // @Value("${personType.teacher}")
    // private String teacherCode;

    @Transactional
    public void save(Attendance entity) {
        attendanceDao.save(entity);
    }

    /**
     * TODO 分页调用
     *
     * @param startTime 2019-08-01T17:30:08+08:00
     * @param endTime 2019-08-10T17:30:08+08:00
     */
    public void call(int pageNo, int pageSize, String startTime, String endTime) {
        JSONObject jsonBody = new JSONObject();
        jsonBody.put("pageNo", pageNo);
        jsonBody.put("pageSize", pageSize);
        jsonBody.put("startTime", startTime);
        jsonBody.put("endTime", endTime);
        log.info("【调用海康前】 pageNo = {}, pageSize = {}, startTime = {}, endTime = {}", pageNo,
            pageSize, startTime, endTime);
        String result = HikService.door(jsonBody);
        log.info("【调用海康后】 pageNo = {}, pageSize = {}, startTime = {}, endTime = {}, result = {}",
            pageNo,
            pageSize, startTime, endTime, result);
        JSONObject resultJson = JSONObject.parseObject(result);
        String code = resultJson.getString("code");
        String msg = resultJson.getString("msg");
        //调用成功
        if ("0".equals(code)) {
            log.info(
                "【调用成功的结果】  pageNo = {} ,pageSize = {} ,startTime = {}, endTime={} ,resultJson =  {}",
                pageNo, pageSize, startTime, endTime, resultJson);
            JSONObject jsonData = resultJson.getJSONObject("data");
            JSONArray jsonArray = jsonData.getJSONArray("list");
            List<Attendance> list = new ArrayList();
            for (Object o : jsonArray) {
                String str = JSONObject.toJSONString(o);
                Attendance attendance = JSONObject.parseObject(str, Attendance.class);
                if (StringUtils.isEmpty(attendance.getPersonId())) {
                    continue;
                }
                try {
                    String eventTime = attendance.getEventTime();
                    if (!StringUtils.isEmpty(eventTime)) {
                        Date date = DateUtil
                            .stringToDate(eventTime, DateUtil.ISO8601_FORMAT);
                        attendance.setEventTime2(date);
                        // attendance.setDanwei(danwei);
                    }
                } catch (Exception e) {
                    log.error("【EventTime时间格式转换错误】 EventTime = {}", attendance.getEventTime());
                }

                list.add(attendance);
            }
            if (list.size() > 0) {
                log.info("【取海康考勤数据待保存到数据库的集合】 list = {}", JsonUtil.toJson(list));
                saveAttendance(list);
            }

        } else {
            log.error(
                "【调用海康接口失败】 code = {} ,msg = {} ,startTime = {} ,endTime = {},pageNo = {},pageSize = {}",
                code, msg,
                startTime, endTime, pageNo, pageSize);
        }
    }

    @Transactional
    public void saveAttendance(List<Attendance> list) {
        attendanceDao.save(list);
    }

    /**
     * 查询起点至今为止所有的数据
     */
    @Transactional
    public void queryAll(String originDate) throws Exception {
        //TODO 先清空数据
        attendanceDao.deleteAll();

        Date start = DateUtil.stringToDate(originDate, DateUtil.FULL_FORMAT);
        Date nowDate = new Date();
        int monthDiff = DateUtil.getMonthDiff(start, nowDate);
        String startDate = "";
        String endDate = "";
        for (int i = 1; i <= monthDiff; i++) {
            if (i == 1) {
                startDate = originDate;
            } else {
                startDate = endDate;
            }
            Date startTime = DateUtil.stringToDate(startDate, DateUtil.FULL_FORMAT);

            Date endTime = DateUtil.subMonth(startDate, 1);

            endDate = DateUtil.dateToString(endTime, DateUtil.FULL_FORMAT);

            String isoStartTime = DateUtil.dateToString(startTime, DateUtil.ISO8601_FORMAT);
            String isoEndTime = DateUtil.dateToString(endTime, DateUtil.ISO8601_FORMAT);

            int pageNo = 1;
            int pageSize = 1000;

            int totalPage = queryTotalPage(pageNo, pageSize, isoStartTime, isoEndTime);
            log.info(
                "【总页码数】 totalPage = {},pageNo = {},pageSize = {},isoStartTime ={},isoEndTime={}",
                totalPage, pageNo, pageSize, isoStartTime, isoEndTime);
            if (totalPage == 0) {
                continue;
            }

            for (int j = pageNo; j <= totalPage; j++) {
                call(j, pageSize, isoStartTime, isoEndTime);
            }

        }
    }

    /**
     * 查询总页数
     */
    public int queryTotalPage(int pageNo, int pageSize, String isoStartTime, String isoEndTime) {
        JSONObject jsonBody = new JSONObject();
        jsonBody.put("pageNo", pageNo);
        jsonBody.put("pageSize", pageSize);
        jsonBody.put("startTime", isoStartTime);
        jsonBody.put("endTime", isoEndTime);
        String result = HikService.door(jsonBody);
        if (result == null) {
            return 0;
        }
        JSONObject resultJson = JSONObject.parseObject(result);
        String code = resultJson.getString("code");
        //调用成功
        if ("0".equals(code)) {
            JSONObject jsonData = resultJson.getJSONObject("data");
            return jsonData.getInteger("totalPage");
        } else {
            return 0;
        }
    }

    //     /**
    //      * 判断出考勤结果
    //      */
    //     public Attendance getAttendanceResult(Attendance attendance){
    //         String normal = "正常";
    //         String late = "迟到";
    //         String loss = "缺卡";
    //         String early = "早退";
    //
    //         //首先开始判断是否是老师
    //         if(!teacherCode.equals(attendance.getOrgIndexCode())){
    //             return attendance;
    //         }
    //         //其次判断冬令时夏令时
    //         if( attendance.getEventTime2() == null  ){
    //             attendance.setAttResult(loss);
    //             return attendance;
    //         }
    //         Date eventTime2 = attendance.getEventTime2();
    //         Date zs_0745 = DateUtil.modifyTime(eventTime2,7,45,0);
    //         Date zs_0746 = DateUtil.modifyTime(eventTime2,7,46,0);
    //         Date zs_0800 = DateUtil.modifyTime(eventTime2,8,0,0);
    //         Date zs_1131 = DateUtil.modifyTime(eventTime2,11,31,0);
    //         Date zs_1415 = DateUtil.modifyTime(eventTime2,14,15,0);
    //         Date zs_1416 = DateUtil.modifyTime(eventTime2,14,16,0);
    //         Date zs_1430 = DateUtil.modifyTime(eventTime2,14,30,0);
    //         Date zs_1345 = DateUtil.modifyTime(eventTime2,13,45,0);
    //         Date zs_1346 = DateUtil.modifyTime(eventTime2,13,46,0);
    //         Date zs_1400 = DateUtil.modifyTime(eventTime2,14,00,0);
    //         Date zs_1730 = DateUtil.modifyTime(eventTime2,17,30,0);
    //         Date zs_1700 = DateUtil.modifyTime(eventTime2,17,00,0);
    // //        if(){
    // //
    // //        }
    //
    //
    //
    //
    //
    //         int month  = DateUtil.getMonth(eventTime2);
    //         //夏令时
    //         // if(month >= 6 && month >= 9){
    //         //     //7.46前
    //         //     if(eventTime2.getTime() < zs_0746.getTime()){
    //         //         attendance.setAttResult(normal);
    //         //     }else if( eventTime2.getTime() >= zs_0746.getTime() && eventTime2.getTime() <= zs_0800.getTime()){
    //         //         attendance.setAttResult(late);
    //         //         //下午
    //         //     }else if (eventTime2.getTime() ){}
    //         //
    //         //     //冬令时
    //         // }else {
    //         //
    //         // }
    //
    //         //判断时间
    //
    //         return null;
    //     }


}
