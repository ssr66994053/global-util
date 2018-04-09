/*
 * www.yiji.com Inc.
 * Copyright (c) 2014 All Rights Reserved
 */

/*
 * 修订记录:
 * boya@yiji.com 2017-06-26 19:16 创建
 *
 */
package com.global.com.global.common.service.utils;

import com.yjf.common.lang.enums.CountryEnum;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author boya@yiji.com
 */
public class CountryUtil {
	
	private static List<CountryEnum> countryEnum = CountryEnum.ABW.getAllEnum();
	
	/**
	 * 得到所有国家的简写
	 * @return
	 */
	public static Map<String, String> getAllCountry() {
		Map<String, String> map = new HashMap();
		for (CountryEnum countryEnum : countryEnum) {
			String shotName = countryEnum.getShotName();
			if (shotName.contains(" ")) {
				map.put(countryEnum.getCode(), shotName.substring(0, shotName.indexOf(" ")));
			} else {
				map.put(countryEnum.getCode(), shotName);
			}
		}
		return map;
	}
	
	/**
	 * 得到所有国家的简写,中国排第一
	 * @return
	 */
	public static Map<String, String> getAllCountryAndChinaFirst() {
		Map<String, String> chinaFirst = new LinkedHashMap<>();
		Map<String, String> allCountry = getAllCountry();
		chinaFirst.put("CHN", "中国");
		chinaFirst.putAll(allCountry);
		return chinaFirst;
	}
}
