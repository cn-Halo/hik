package com.hik.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hik.dao.FaceAttendanceDao;
import com.hik.entity.FaceAttendance;
import com.hik.enums.ResultEnum;
import com.hik.exception.PayException;
import com.hik.util.DateUtil;
import com.hik.util.HttpUtil;
import com.hik.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.httpclient.NameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.*;

@Service
@Slf4j
public class FaceAttendanceService {


    @Autowired
    private FaceAttendanceDao faceAttendanceDao;


    private final static String ADDRESS = "http://192.168.30.200";
    private final static String SUFFIX = "/person/record/query/time";

    /**
     * 取人脸考勤数据
     * 出入记录按起止时间查询
     *
     * @param startTime
     * @param endTime
     */
    public void queryWithTime(String startTime, String endTime) {
        NameValuePair[] params = new NameValuePair[]{
                new NameValuePair("start_time", startTime),
                new NameValuePair("end_time", endTime)
        };
        Map<String, Object> resp = new HashMap<>();
        try {
            log.info("【调用人脸考勤出入记录按起止时间查询接口前】startTime = {} , endTime = {}  ", startTime, endTime);
            resp = HttpUtil.post(ADDRESS + SUFFIX, params);
            log.info("【调用人脸考勤出入记录按起止时间查询接口后】startTime = {} , endTime = {} ,resp ={}", startTime, endTime, resp);
        } catch (Exception e) {
            log.error("【调用人脸考勤出入记录按起止时间查询接口失败】startTime = {} , endTime = {} ,e={}", startTime, endTime, e);
            throw new PayException(ResultEnum.CALL_FACE_INTERFACE_FAIL);
        }
        Object statusCode = resp.get("statusCode");
        String successStatusCode = "200";
        if (statusCode != null && successStatusCode.equals(String.valueOf(statusCode))) {
            JSONObject jsonObject = (JSONObject) resp.get("responseBody");
            boolean result = jsonObject.getBoolean("success");
            if (!result) {
                log.error("【调用人脸考勤出入记录按起止时间查询接口获取数据失败】startTime = {} , endTime = {},resp = {}", startTime, endTime, resp);
                throw new PayException(ResultEnum.CALL_FACE_INTERFACE_FAIL);
            }
            JSONArray jsonArray = jsonObject.getJSONArray("data");
            List<FaceAttendance> list = new ArrayList<>();
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject faceJSON = jsonArray.getJSONObject(i);
                String jsonStr = JSONObject.toJSONString(faceJSON);
                FaceAttendance faceAttendance = JSONObject.parseObject(jsonStr, FaceAttendance.class);
                faceAttendance.setId(null);
                list.add(faceAttendance);
            }
            if (list.size() > 0) {
                log.info("【调用人脸考勤出入记录按起止时间查询接口后待保存数据库集合】 list = {}", JsonUtil.toJson(list));
                save(list);
            }
        } else {
            log.error("【调用人脸考勤出入记录按起止时间查询接口失败】startTime = {} , endTime = {},resp = {}", startTime, endTime, resp);
            throw new PayException(ResultEnum.CALL_FACE_INTERFACE_FAIL);
        }

    }

    @Transactional
    public void init() {
        String startTime = "2020-06-15 00:00:00";
        String endTime = DateUtil.getCurrentDate(DateUtil.FULL_FORMAT);
        faceAttendanceDao.deleteAll();
        queryWithTime(startTime, endTime);
    }


    private final static String SUFFIX2 = "/person/totalCount";

    /**
     * 获取入库总人数
     *
     * @return
     */
    public int queryTotal() {
        //默认总条数
        int defaultTotalCount = 10000;
        NameValuePair[] params = new NameValuePair[]{};
        try {
            Map<String, Object> resp = HttpUtil.post(ADDRESS + SUFFIX2, params);
            log.info("【调用人脸考勤获取入库总人数接口后】resp ={}", resp);
            Object statusCode = resp.get("statusCode");
            String successStatusCode = "200";
            if (statusCode != null && successStatusCode.equals(String.valueOf(statusCode))) {
                JSONObject jsonObject = (JSONObject) resp.get("responseBody");
                boolean result = jsonObject.getBoolean("success");
                if (result) {
                    int totalCount = jsonObject.getInteger("data");
                    return totalCount;
                } else {
                    log.error("【调用人脸考勤获取入库总人数接口获取数据失败】resp = {}", resp);
                }

            }
        } catch (IOException e) {
            log.error("【调用人脸考勤获取入库总人数接口失败】e = {}", e);
        }
        return defaultTotalCount;

    }

    private final static String SUFFIX3 = "/person/query/part";

    /**
     * 1.5人员分页查询接口
     *
     * @param index
     * @param count
     */
    public List<FaceAttendance> queryByPage(String index, String count) {
        NameValuePair[] params = new NameValuePair[]{
                new NameValuePair("index", index),
                new NameValuePair("count", count)
        };
        Map<String, Object> resp = new HashMap<>();
        try {
            log.info("【调用人脸考勤分页查询接口前】index = {} , count = {}  ", index, count);
            resp = HttpUtil.post(ADDRESS + SUFFIX3, params);
        } catch (Exception e) {
            log.error("【调用人脸考勤分页查询接口失败】index = {} , count = {} ,e={}", index, count, e);
            throw new PayException(ResultEnum.CALL_FACE_INTERFACE_FAIL);
        }
        log.info("【调用人脸考勤分页查询接口前后】index = {} , count = {} ,resp ={}", index, count, resp);
        Object statusCode = resp.get("statusCode");
        String successStatusCode = "200";
        if (statusCode != null && successStatusCode.equals(String.valueOf(statusCode))) {
            JSONObject jsonObject = (JSONObject) resp.get("responseBody");
            boolean result = jsonObject.getBoolean("success");
            if (!result) {
                log.error("【调用人脸考勤分页查询接口获取数据失败】index = {} , count = {},resp = {}", index, count, resp);
                throw new PayException(ResultEnum.CALL_FACE_INTERFACE_FAIL);
            }
            JSONArray jsonArray = jsonObject.getJSONArray("data");
            List<FaceAttendance> list = new ArrayList<>();
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject faceJSON = jsonArray.getJSONObject(i);
                String jsonStr = JSONObject.toJSONString(faceJSON);
                FaceAttendance faceAttendance = JSONObject.parseObject(jsonStr, FaceAttendance.class);
//                faceAttendance.setId(null);
                list.add(faceAttendance);
            }
//            if (list.size() > 0) {
//                    log.info("【调用人脸考勤分页查询接口后待保存数据库集合】 list = {}", JsonUtil.toJson(list));
//                    save(list);
//                return list;
//            }
            return list;
        } else {
            log.error("【调用人脸考勤分页查询接口失败】index = {} , count = {},resp = {}", index, count, resp);
            throw new PayException(ResultEnum.CALL_FACE_INTERFACE_FAIL);
        }

    }


    @Transactional
    public void save(List<FaceAttendance> list) {
        faceAttendanceDao.save(list);
    }

}
