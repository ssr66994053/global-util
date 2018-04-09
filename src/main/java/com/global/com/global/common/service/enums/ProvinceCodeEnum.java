/*
 * www.yiji.com Inc.
 * Copyright (c) 2014 All Rights Reserved
 */

/*
 * 修订记录:
 * boya@yiji.com 2016-05-04 20:17 创建
 *
 */
package com.global.com.global.common.service.enums;

/**
 * @author boya@yiji.com
 */
public enum ProvinceCodeEnum {
    BJS("11","北京市"),
    TJS("12","天津市"),
    HBS("13","河北省"),
    SXS("14","山西省"),
    NMGZZQ("15","内蒙古自治区"),
    LNS("21","辽宁省"),
    JLS("22","吉林省"),
    HLJS("23","黑龙江省"),
    SHS("31","上海市"),
    JSS("32","江苏省"),
    ZJS("33","浙江省"),
    AHS("34","安徽省"),
    FJS("35","福建省"),
    JXS("36","江西省"),
    SDS("37","山东省"),
    HNS("41","河南省"),
    HUBEIS("42","湖北省"),
    HUNANS("43","湖南省"),
    GDS("44","广东省"),
    GXZZZZQ("45","广西壮族自治区"),
    HENANS("46","海南省"),
    CQS("50","重庆市"),
    SCS("51","四川省"),
    GZS("52","贵州省"),
    YNS("53","云南省"),
    XZZZQ("54","西藏自治区"),
    SANXIS("61","陕西省"),
    GSS("62","甘肃省"),
    QHS("63","青海省"),
    NXHZZZQ("64","宁夏回族自治区"),
    XJWWEZZQ("65","新疆维吾尔自治区"),
    TWS("71","台湾省"),
    XGTBXZQ("81","香港特别行政区"),
    AMTBXZQ("82","澳门特别行政区"),
	;
	/** 身份证省市代码 */
	private final String	code;
							
	/** 省市名 */
	private final String	provinceName;
							
	/**
	 *
	 * @param code 枚举值
	 * @param provinceName 枚举描述
	 */
	private ProvinceCodeEnum(String code, String provinceName) {
		this.code = code;
		this.provinceName = provinceName;
	}
	
	/**
	 * @return Returns the code.
	 */
	public String getCode() {
		return code;
	}
	
	/**
	 * @return Returns the provinceName.
	 */
	public String getMessage() {
		return provinceName;
	}
	
	public static ProvinceCodeEnum getByCode(String code) {
		for (ProvinceCodeEnum _enum : values()) {
			if (_enum.getCode().equals(code)) {
				return _enum;
			}
		}
		return null;
	}
	
	public static java.util.List<ProvinceCodeEnum> getAllEnum() {
		java.util.List<ProvinceCodeEnum> list = new java.util.ArrayList<ProvinceCodeEnum>(
			values().length);
		for (ProvinceCodeEnum _enum : values()) {
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
		java.util.List<String> list = new java.util.ArrayList<String>(values().length);
		for (ProvinceCodeEnum _enum : values()) {
			list.add(_enum.getCode());
		}
		return list;
	}
	
	/**
	 * 通过code获取provinceName
	 * @param code 枚举值
	 * @return
	 */
	public static String getProvinceName(String code) {
		if (code == null) {
			return null;
		}
		ProvinceCodeEnum _enum = getByCode(code);
		if (_enum == null) {
			return null;
		}
		return _enum.getMessage();
	}
}
