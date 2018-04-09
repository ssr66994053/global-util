/*
 * www.yiji.com Inc.
 * Copyright (c) 2014 All Rights Reserved
 */

/*
 * 修订记录:
 * linlin@yiji.com 2017-04-26 17:15 创建
 *
 */
package com.global.com.global.common.service;

import com.global.com.global.common.service.annotation.Document;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * @author linlin@yiji.com
 */
public abstract class AbstractBizOrder extends AbstractOrder {
    private static final long serialVersionUID = -5123282717072037088L;
    /**
     * 请求流水 用来标识每次请求 每一次新的请求都需要重新生成 gid+reqId组合起来做幂等性校验
     */
    @Document(name = "请求流水号",desc = "用来标识每次请求 每一次新的请求都需要重新生成 gid+reqId组合起来做幂等性校验",demo = "20170411120068005139")
    @NotNull(message = "请求流水号不能为空")
    @Length(min = 20, max = 20)
    protected String reqId;

    /**
     * 易极为商户分配的卡号(cardNo), 必填
     */
    @Document(name = "商户ID",desc = "由商户系统分配的商户ID，用于标识商户的身份",demo = "20170411000068000001")
    @NotNull
    @Length(min = 20, max = 20)
    private String partnerId;
    /**
     * 外部商户请求的交易订单号，必填
     */
    @NotNull
    @Length(min = 1, max = 64)
    @Document(name = "商户订单号",desc = "外部商户请求的交易订单号",demo = "008y00901gx8b14e8o01")
    private String merchOrderNo;

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public String getMerchOrderNo() {
        return merchOrderNo;
    }

    public void setMerchOrderNo(String merchOrderNo) {
        this.merchOrderNo = merchOrderNo;
    }

    public String getReqId() {
        return reqId;
    }

    public void setReqId(String reqId) {
        this.reqId = reqId;
    }
}
