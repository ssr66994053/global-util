/*
 * www.yiji.com Inc.
 * Copyright (c) 2014 All Rights Reserved
 */

/*
 * 修订记录:
 * linlin@yiji.com 2017-04-26 16:10 创建
 *
 */
package com.global.com.global.common.service;

import com.global.com.global.common.service.annotation.Document;

/**
 * @author linlin@yiji.com
 */

@Document(name = "业务处理结果", desc = "业务服务处理的返回结果，带业务记录信息")
public class BizResult<I> extends SimpleResult {
	private static final long	serialVersionUID	= -2275003133090835779L;
													
	/**
	 * 结果信息
	 */
	@Document(name = "结果信息", desc = "业务服务处理的返回结果的扩展业务信息")
	private I					info;
								
	public static <T> BizResult<T> newInstance() {
		return new BizResult<>();
	}
	
	public I getInfo() {
		return info;
	}
	
	public void setInfo(I info) {
		this.info = info;
	}
}
