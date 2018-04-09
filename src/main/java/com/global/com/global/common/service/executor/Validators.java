/*
 * www.yiji.com Inc.
 * Copyright (c) 2014 All Rights Reserved
 */

/*
 * 修订记录:
 * linlin@yiji.com 2017-06-16 11:18 创建
 *
 */
package com.global.com.global.common.service.executor;

import com.yiji.adk.common.Constants;
import com.yiji.adk.common.exception.BizException;
import com.yiji.adk.common.exception.IllegalParameterException;
import com.yiji.adk.common.exception.SystemException;
import com.yjf.common.service.Order;

/**
 * @author linlin@yiji.com
 */
public class Validators {
	
	private Validators() {
	}
	
	public static void checkParameterIfNeedForGlobal(Object parameter) {
		try {
			if (parameter != null) {
				if (parameter instanceof Order) {
					((Order) parameter).check();
				}
			}
		} catch (Exception e) {
			if (e instanceof SystemException) {
				throw (SystemException) e;
			}
			BizException error = new IllegalParameterException(
				String.format("单据错误:%s", e.getMessage()));
			error.setErrorCode(Constants.ERROR_CODE_ILLEGA_PARAMETER);
			throw error;
		}
	}
}
