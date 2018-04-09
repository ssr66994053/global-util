/*
 * www.yiji.com Inc.
 * Copyright (c) 2015 All Rights Reserved.
 */

/*
 * 修订记录：
 * shiguang 2016年3月7日 下午2:30:18 创建
 */
    
package com.global.com.global.common.service.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 *
 * @author shiguang
 *
 */

public class BusinessNumberUtil {
	
	public static String gainNumber() {
		StringBuffer number = new StringBuffer();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
				"yyyyMMddHHmmssSSS");
		number.append(simpleDateFormat.format(new Date()));
		int a = (int) (Math.random() * 1000);
		if (a < 10 && a > 0) {
			a = a * 100;
		} else if (a >= 10 && a < 100) {
			a = a * 10;
		}
		number.append(a == 0 ? "000" : a);
		return number.toString();
	}
	
}

    