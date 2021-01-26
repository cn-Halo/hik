package com.hik.vo;

import lombok.Data;

import java.util.Date;

/**
 * Created by yzm on 2017/11/6.
 * 发票的VO
 */
@Data
public class InvoiceVO {

    private String outTradeNo;//商户订单号
    private String gmtPayment;//付款时间
    private String gfmc;//购方名称
    private String gfsh;//购方税号 即购方纳税人识别号
    private String gfdzdh;//地址、电话
    private String gfyhzh;//开户行及账号
    private String spmc;//货物或应税劳务、服务名称  | 商品名称
    private String spbm;//商品编码
    private String ggxh;//规格型号
    private String jldw;//单位
    private String sl;//数量
    private String dj;//单价
    private String je;//金额
    private String slv;//税率
    private String jshj;//价税合计 即总价
    private String email; // 收件人邮箱
    private String address;//收件人地址
    private String phone;//收件人电话
    private String name;//收件人姓名
    private String isDraw ;// 是否已经开过发票  0：未开 1：已开
    private Date createTime;//创建时间
    private String remoteSysName;//系统名称

}
