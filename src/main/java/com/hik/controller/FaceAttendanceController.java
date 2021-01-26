package com.hik.controller;

import com.hik.entity.FaceAttendance;
import com.hik.service.FaceAttendanceService;
import com.hik.vo.ResponseVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@RestController
@RequestMapping("/face")
@ApiIgnore
public class FaceAttendanceController {

    @Autowired
    private FaceAttendanceService faceAttendanceService;

//    @ApiOperation(value = "按时间段查询人脸考勤并保存")
//    @GetMapping
//    public ResponseVO queryAll(String startTime, String endTime) {
//        faceAttendanceService.queryWithTime(startTime, endTime);
//        return ResponseVO.defaultResp();
//    }

    @ApiOperation(value = "初始化导入所有人脸数据")
    @GetMapping("/init")
    public ResponseVO init(){
        faceAttendanceService.init();
        return ResponseVO.defaultResp();
    }


    @ApiOperation(value = "人员分页查询接口")
    @GetMapping("/queryByPage")
    public ResponseVO init(String index,String count){
        List <FaceAttendance>  list =  faceAttendanceService.queryByPage(index,count);
       return new ResponseVO(list);
    }

//    @ApiOperation(value = "获取入库总人数")
//    @GetMapping("/queryTotal")
//    public ResponseVO queryTotal(){
//        faceAttendanceService.queryTotal();
//        return ResponseVO.defaultResp();
//    }

}
