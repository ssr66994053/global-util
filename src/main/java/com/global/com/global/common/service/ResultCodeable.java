/*
 * www.yiji.com Inc.
 * Copyright (c) 2014 All Rights Reserved
 */

/*
 * 修订记录:
 * linlin@yiji.com 2017-04-26 15:45 创建
 *
 */
package com.global.com.global.common.service;

import com.global.com.global.common.service.annotation.Document;

/**
 * @author linlin@yiji.com
 */
@Document(name = "结果码定义接口", desc = "实现该接口的表示为结果码信息")
public interface ResultCodeable {
	
	/**
	 * 结果码
	 * @return
	 */
	String getCode();
	
	/**
	 * 结果码描述信息
	 * @return
	 */
	String getMessage();
}
