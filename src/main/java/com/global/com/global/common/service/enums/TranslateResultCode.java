/*
 * www.yiji.com Inc.
 * Copyright (c) 2014 All Rights Reserved
 */

/*
 * 修订记录:
 * linlin@yiji.com 2017-04-26 15:42 创建
 *
 */
package com.global.com.global.common.service.enums;

import com.global.com.global.common.service.ResultCodeable;

/**
 * @author linlin@yiji.com
 */
public enum  TranslateResultCode implements ResultCodeable{
    //公共
    COMN_SUCCESS("comn_04_0001", "处理成功"),

    EXECUTE_SUCCESS(COMN_SUCCESS.code(),COMN_SUCCESS.message()),
    RUNNING_IN_PROCESS("comn_04_0002","业务处理中"),

    COMN_REQUEST_REPEAT("comn_04_0004", "系统处理中,请耐心等待"),

    ERROR_CODE_ILLEGA_PARAMETER("comn_04_0003", "业务失败,请联系客服"),
    ERROR_CODE_SUPPEND("S064_02_0012", "系统处理中,请耐心等待"),
    ERROR_CODE_UNKOWN("comn_04_0000", "业务失败,请联系客服"),
    ERROR_CODE_NEST("comn_03_0000", "业务失败,请联系客服"),

    //网关
    SUCCESS("GT_000_0001", "成功"),
    CHECKFAIL("GT_001_0001", "校验失败"),
    DUPFAIL("GT_001_0002", "重复交易"),
    PROTFAIL("GT_002_0002", "银行网关失败"),
    BANKFAIL("GT_002_0001", "银行失败"),
    BANKSPENDING("GT_002_0002", "银行处理中"),
    INVAIDNO("GT_002_0003", "查询号码不存在"),
    SIGFAIL("GT_003_0001", "验证签名失败"),
    MESGFAIL("GT_006_0001", "数据传输失败"),
    DATABASE("GT_004_0001", "数据库失败"),

    //账务
    NO_ACCOUNT("L007_00_0001", "未找到账户"),
    ACCOUNT_EXISTS("L007_00_0002", "账户已存在"),
    CURRENCY_DIFF("L007_00_0003", "账户币种不匹配"),
    BALANCE_SHORT("L007_01_0001", "余额不足"),
    CREDIT_BALANCE_SHORT("L007_01_0002", "可用信用余额不足"),
    CREDIT_AMOUNT_SHORT("L007_01_0003", "信用额度不足"),

    //保证金
    NO_USER("L009_00_0001", "未找到用户"),
    USER_CREDITED("L009_00_0002", "用户授信已存在"),
    NO_USER_CREDITED("L009_00_0003", "未找到用户授信配置"),
    USER_CREDIT_OFF("L009_00_0004", "用户授信配置未启用"),
    USER_CREDIT_CUT("L009_00_0005", "用户授信已熔断"),
    USER_STATUS_ERROR("L009_00_0006", "账户状态错误"),
    RISK_ERROR("L009_00_0007", "风险覆盖系数大于等于1"),
    NO_REQUEST_ID("L009_01_0001", "不存在的外部请求号"),
    STATUS_ERROR("L009_01_0002", "业务状态不正确"),
    INSURANCE_DEPOSIT_REPEAT("L009_01_0003", "保证金充值时,使用了以前使用过的充值流水."),
    NO_DATA("L009_02_0001", "未查询到数据."),

    //对账
    OPERATION_NOT_SUPPORT("L011_00_0001", "不支持的操作"),
    NO_RECONDATA("L011_00_0002", "对账数据不存在"),
    DATA_STATUS_ERROR("L011_00_0003", "数据状态不对"),
    NO_TASK_OF_DOWNLOAD_BANK("L011_00_0004", "下载银行对账任务不存在"),
    REPEAT_TASK_OF_DOWNLOAD_BANK("L011_00_0005", "下载银行对账任务已存在"),
    DOWNLOAD_BANK_ERROR("L011_00_0006", "下载银行对账文件失败"),
    RECON_INVOKE_FILECENTER_ERROR("L011_00_0007", "调用文件中心解析对账文件失败"),

    //数据上报
    FILE_ASSEMBLE_FAILED("L012_00_00", "文件组装失败"),
    GATEWAY_UPLOAD_ERROR("L012_00_01", "上传至网关出错"),
    NULL_QUERY_RESULT("L012_00_02", "查询结果集为空"),
    REPAY_SUMMARY_FAILED("L012_00_03", "还款汇总出错"),

    //文件服务
    NO_FILE("comn_01_0100", "文件不存在,请确认"),
    FILE_ERROR("comn_00_0101", "文件非法,可能是文件命名不对"),
    NO_LOCAL_FILE("comn_01_0101", "本地文件不存在"),

    //风控
    RISKx000000("L010_00_0000", "执行成功"),
    RISKx006666("L010_00_6666", "执行过程中"),
    RISKx009999("L010_00_9999", "执行失败"),
    RISKx002222("L010_00_2222", "内部错误"),
    RISKx000010("L010_01_0010", "风控参数校验失败"),
    RISKx000001("L010_01_0001", "消金公司评分卡为空"),
    RISKx000002("L010_01_0002", "个人评分卡为空"),
    RISKx000003("L010_01_0003", "个人征信数据异常"),
    RISKx000004("L010_01_0004", "事件流水中业务参数异常"),
    RISKx000005("L010_01_0005", "通知网关获取数据异常"),
    RISKx300001("L010_03_0001", "人工审核失败"),
    RISKx010003("L010_02_0003", "唯一索引校验失败，幂等异常"),

    //订单
    INVALID_BUSINESS_STATUS("L006_00_00", "错误的业务状态"),
    NO_WAIT_REPAY_PLAN("L006_04_0000", "贷款已结清或者全部逾期"),
    REPAY_DETAIL_SEND_FAIL("L006_04_0001", "还款明细推送文件服务失败"),
    UNMATCHED_AMOUNT("L006_04_0002", "金额不匹配"),
    REPAY_CREDIT_FAIL("L006_04_0003", "归还授信额度失败"),
    INVOKE_BANK_FAIL("L006_04_0004", "调用银行系统失败"),
    INVOKE_REMOTE_FAIL("L006_04_0005", "调用远程系统失败"),
    TARGET_OBJECT_NO_EXIST("L006_04_0006", "目标对象不存在"),
    ORDER_NO_EXIST("G000_00_0000", "贷款订单不存在"),
    ORDER_APPLY_EXCEPTION("G000_00_0001", "贷款申请异常，请联系相关技术人员"),
    APPLY_RISK("G000_00_0002", "风控返回失败"),
    APPLY_PROCESSING("G000_00_0003", "贷款申请处理中"),
    ORDER_RISK_RETRY("G000_00_0004", "当前订单状态不允许风控回推结果"),
    APPLY_FAIL("G000_00_0005", "贷款申请失败"),
    DISCHARGE_NOT_APPLY_SUCCESS("G000_00_0006", "当前订单状态不是贷款申请成功，不允许放款"),
    DISCHARGE_NOT_SUCCESS_PROCESSING("G000_00_0007", "当前订单状态不是贷款申请成功或放款处理中，不允许放款"),
    DISCHARGE_NOT_RETRY("G000_00_0008", "当前订单不允许重复放款"),
    DISCHARGE_PROCESSING("G000_00_0009", "放款业务处理中"),
    DISCHARGE_FAIL("G000_00_0010", "放款失败"),
    QUERY_LOAN_ORDER("G000_00_0011", "查询放款状态异常"),
    QUERY_LOAN_ERROR("G000_00_0012", "无此贷款申请记录，无此借据号"),
    PAYMENT_FAIL_PROCESSING_SUCCESS("G000_00_0013", "当前订单状态不是支付失败、支付处理中、或者放款成功，不允许重新支付"),
    PAYMENT_PROCESSING("G000_00_0014", "支付处理中"),
    PAYMENT_SUCCESS("G000_00_0015", "支付成功"),
    PAYMENT_FAIL("G000_00_0016", "支付失败"),
    QUERY_PAYMENT_ORDER("G000_00_0017", "查询支付状态异常"),
    TAKE_EXCEPTION_ERROR("G000_00_0018", "异提异常"),
    ORDER_STATUS_ERROR("G000_00_0019", "订单状态不正确"),
    ORDER_PAYMENT_EXCEPTION("G000_00_0020", "已进入异提还款流程，无法重新支付");
    /**
     * 枚举值
     */
    private final String code;

    /**
     * 枚举描述
     */
    private final String message;

    /**
     * 构造一个<code>TranslateResultCode</code>枚举对象
     *
     * @param code
     * @param message
     */
    TranslateResultCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * @return Returns the code.
     */
    public String getCode() {
        return code;
    }

    /**
     * @return Returns the message.
     */
    public String getMessage() {
        return message;
    }

    /**
     * @return Returns the code.
     */
    public String code() {
        return code;
    }

    /**
     * @return Returns the message.
     */
    public String message() {
        return message;
    }

    public static String getMessageByCode(String code) {
        TranslateResultCode _enum = getByCode(code);
        if (_enum == null) {
            _enum = ERROR_CODE_UNKOWN;
        }
        return _enum.getMessage();
    }

    /**
     * 通过枚举<code>code</code>获得枚举
     *
     * @param code
     * @return TranslateResultCode
     */
    public static TranslateResultCode getByCode(String code) {
        for (TranslateResultCode _enum : values()) {
            if (_enum.getCode().equalsIgnoreCase(code)) {
                return _enum;
            }
        }
        return ERROR_CODE_UNKOWN;
    }

    /**
     * 获取全部枚举
     *
     * @return List<LocalCacheEnum>
     */
    public static java.util.List<TranslateResultCode> getAllEnum() {
        java.util.List<TranslateResultCode> list = new java.util.ArrayList<TranslateResultCode>();
        for (TranslateResultCode _enum : values()) {
            list.add(_enum);
        }
        return list;
    }

    /**
     * 获取全部枚举值
     *
     * @return List<String>
     */
    public static java.util.List<String> getAllEnumCode() {
        java.util.List<String> list = new java.util.ArrayList<String>();
        for (TranslateResultCode _enum : values()) {
            list.add(_enum.code());
        }
        return list;
    }

    /**
     * 判断给定的枚举，是否在列表中
     *
     * @param values 列表
     * @return
     */
    public boolean isInList(TranslateResultCode... values) {
        for (TranslateResultCode e : values) {
            if (this == e) {
                return true;
            }
        }
        return false;
    }
}
