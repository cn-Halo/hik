package com.hik.vo;

import lombok.Data;

/**
 * Created by yzm on 2017/8/23.
 */
@Data
public class QueryInfoVO {
    private String subCode;//业务返回码
    private String subMsg;//业务返回码描述
    private String code;//网关返回码
    private String msg;//网关返回码描述,
    private String buyerLogonId;//买家支付宝账号
    private String buyerPayAmount;//买家实付金额
    private String buyerUserId;//买家在支付宝的用户id
    private String invoiceAmount;//交易中用户支付的可开具发票的金额
    private String openId;
    private String outTradeNo;//商家订单号
    private String pointAmount;//	积分支付的金额
    private String receiptAmount;//实收金额
    private String sendPayDate;//本次交易打款给卖家的时间
    private String totalAmount;//交易的订单金额
    private String tradeNo;//支付宝交易号
    private String tradeStatus;//交易状态：WAIT_BUYER_PAY（交易创建，等待买家付款）、TRADE_CLOSED（未付款交易超时关闭，或支付完成后全额退款）、TRADE_SUCCESS（交易支付成功）、TRADE_FINISHED（交易结束，不可退款）
    private String tradeStatusNote;//交易状态说明*

//    private String storeId;//	商户门店编号..
//    private String terminalId;//商户机具终端编号..
//    private String fundBillList;//交易支付使用的资金渠道..
}
