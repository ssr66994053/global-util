/*
 * www.yiji.com Inc.
 * Copyright (c) 2014 All Rights Reserved
 */

/*
 * 修订记录:
 * linlin@yiji.com 2017-06-16 11:22 创建
 *
 */
package com.global.com.global.common.service.boot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.global.com.global.common.service.executor.GlobalExecutor;

/**
 * @author linlin@yiji.com
 */
@Configuration
public class ExecutorConfiguration {
	
	@Bean
	public GlobalExecutor globalExecutor() {
		return new GlobalExecutor();
	}
}
