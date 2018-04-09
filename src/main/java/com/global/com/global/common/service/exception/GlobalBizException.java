/*
 * www.yiji.com Inc.
 * Copyright (c) 2014 All Rights Reserved
 */

/*
 * 修订记录:
 * linlin@yiji.com 2017-06-16 14:21 创建
 *
 */
package com.global.com.global.common.service.exception;

import com.yiji.adk.common.exception.BizException;
import com.yjf.common.lang.result.Status;

/**
 * @author linlin@yiji.com
 */
public class GlobalBizException extends BizException {
	@Override
	protected String defaultErrorCode() {
		return "GlobalBizException";
	}
	
	public GlobalBizException(	String message, String errorCode, Status status,
								Throwable throwable) {
		super(message, errorCode, status, throwable);
	}
}
