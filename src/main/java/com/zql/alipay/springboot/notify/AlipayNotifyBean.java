package com.zql.alipay.springboot.notify;

import lombok.Data;

import java.math.BigDecimal;

/***
 * 支付宝异步通知参数 https://docs.open.alipay.com/203/105286/
 *
 * @author 张乾龙
 * @date: 2019.11.20
 */
@Data
public class AlipayNotifyBean {

    private String notify_time;

    private String notify_type;

    private String notify_id;

    private String app_id;

    private String charset;

    private String version;

    private String sign_type;

    private String sign;

    private String trade_no;

    private String out_trade_no;

    private String out_biz_no;

    private String buyer_id;

    private String buyer_logon_id;

    private String seller_id;

    private String seller_email;

    private String trade_status;

    private BigDecimal total_amount;

    private BigDecimal receipt_amount;

    private BigDecimal invoice_amount;

    private BigDecimal buyer_pay_amount;

    private BigDecimal point_amount;

    private BigDecimal refund_fee;

    private String subject;

    private String body;

    private String gmt_create;

    private String gmt_payment;

    private String gmt_refund;

    private String gmt_close;

    private String fund_bill_list;

    private String passback_params;

    private String voucher_detail_list;


}
