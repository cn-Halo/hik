package com.hik.vo;

import lombok.Data;

/**
 * Created by yzm on 2017/12/7.
 * 导入发票的excel对应的VO
 */
@Data
public class GivenInvoiceVO {

    private String outTradeNo;
    private String fpzl;//发票种类
    private String fpdm;//发票代码
    private String fphm;//发票号码
    private String gfmc;//购方名称
    private String gfsh;//购方税号
    private String kprq;//开票日期
    private String je;//合计金额
    private String slv;//税率
    private String kce;//合计税额
    private String jshj;//价税合计
    private String spmc;//主要商品名称
    private String bz;//备注
    private String zfbz;//作废标志
    private String qdbz;//清单标志
    private String xfmc; //销方名称
    private String xfsh; //销方税号
    private String xfdzdh;//销方地址电话
    private String xfyhzh;//销方银行账号
    private String bszt;//报送状态
}
