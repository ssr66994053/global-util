/*
 * www.yiji.com Inc.
 * Copyright (c) 2014 All Rights Reserved
 */

/*
 * 修订记录:
 * linlin@yiji.com 2017-04-27 17:19 创建
 *
 */
package com.global.com.global.common.service;

import java.sql.DatabaseMetaData;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.global.com.global.common.service.mybatis.ExtraMapper;
import com.global.com.global.common.service.mybatis.MysqlExtraImpl;
import com.global.com.global.common.service.mybatis.OracleExtraImpl;
import com.yjf.common.concurrent.MonitoredThreadPool;

/**
 * @author linlin@yiji.com
 */
@Configuration
public class GlobalCommonConfiguration {
	
	@Resource
	private DataSource dataSource;
	
	@Bean
	public ExtraMapper extraMapper() throws SQLException {
		ExtraMapper extraMapper;
		DatabaseMetaData md = dataSource.getConnection().getMetaData();
		if ("MYSQL".equalsIgnoreCase(md.getDatabaseProductName())) {
			extraMapper = new MysqlExtraImpl();
		} else {
			extraMapper = new OracleExtraImpl();
		}
		return extraMapper;
	}
	
	@Bean(name = "GlobalCommonThreadPoolTaskExecutor")
	public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
		MonitoredThreadPool taskExecutor = new MonitoredThreadPool();
		taskExecutor.setCorePoolSize(5);
		taskExecutor.setKeepAliveSeconds(300);
		taskExecutor.setMaxPoolSize(100);
		taskExecutor.setQueueCapacity(50);
		taskExecutor.setAwaitTerminationSeconds(60);
		taskExecutor.setWaitForTasksToCompleteOnShutdown(true);
		taskExecutor.setDaemon(false);
		taskExecutor.setThreadNamePrefix("Cache-");
		taskExecutor.initialize();
		return taskExecutor;
	}
}
