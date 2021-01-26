package com.hik.enums;

import lombok.Getter;

/**
 * Created by yzm on 2017/8/22.
 * 交易状态说明
 */
@Getter
public enum TradeStatusEnum implements CodeEnum {
    WAIT_BUYER_PAY(0, "交易创建，等待买家付款"),
    TRADE_CLOSED(1, "未付款交易超时关闭，或支付完成后全额退款"),
    TRADE_SUCCESS(2, "交易支付成功"),
    TRADE_FINISHED(3, "交易结束，不可退款"),;

    private Integer code;
    private String note;

    TradeStatusEnum(Integer code, String note) {
        this.code = code;
        this.note = note;
    }
}
