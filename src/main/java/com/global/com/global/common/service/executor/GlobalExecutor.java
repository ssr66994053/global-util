/*
 * www.yiji.com Inc.
 * Copyright (c) 2014 All Rights Reserved
 */

/*
 * 修订记录:
 * linlin@yiji.com 2017-06-16 11:04 创建
 *
 */
package com.global.com.global.common.service.executor;

import org.springframework.beans.factory.annotation.Autowired;

import com.global.com.global.common.service.SimpleResult;
import com.yiji.adk.biz.executor.ActivityExecutorContainer;
import com.yjf.common.lang.context.OperationContext;

/**
 * @author linlin@yiji.com
 */
public class GlobalExecutor {
	
	@Autowired
	private ActivityExecutorContainer activityExecutorContainer;
	
	public <Param, R extends SimpleResult> R accept(Param parameter, String serviceName,
													OperationContext operationContext) {
		return activityExecutorContainer.accept(parameter, serviceName, operationContext);
		
	}
	
}
