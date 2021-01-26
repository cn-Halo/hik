package com.hik.vo;

import lombok.Data;

/**
 * Created by yzm on 2017/8/24.
 */
@Data
public class NotifyInfoVO {

    private String outTradeNo;//商户订单号
    private String outBizNo;//商户业务号
    private String tradeNo;//支付宝交易号
    private String buyerId; //买家支付宝用户号
    private String sellerId;//卖家支付宝用户号
    private String totalAmount;//订单金额
    private String receiptAmount;//实收金额
    private String invoiceAmount;//开票金额
    private String buyerPayAmount;//付款金额
    private String pointAmount;//集分宝金额
    private String gmtCreate;//交易创建时间
    private String gmtPayment;//交易付款时间
    private String tradeStatus;//交易状态
}
