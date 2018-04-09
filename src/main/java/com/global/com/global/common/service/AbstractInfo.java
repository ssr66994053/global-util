/*
 * www.yiji.com Inc.
 * Copyright (c) 2014 All Rights Reserved
 */

/*
 * 修订记录:
 * linlin@yiji.com 2017-04-27 11:59 创建
 *
 */
package com.global.com.global.common.service;

import java.io.Serializable;

import com.global.com.global.common.service.annotation.Document;
import com.yjf.common.util.ToString;

/**
 * @author linlin@yiji.com
 */
@Document(name = "返回结果对象父类", desc = "返回结果对象DTO")
public class AbstractInfo implements Serializable {
	private static final long serialVersionUID = 1269084801777078566L;
	
	@Override
	public String toString() {
		return ToString.toString(this);
	}
}
