/*
 * www.yiji.com Inc.
 * Copyright (c) 2014 All Rights Reserved
 */

/*
 * 修订记录:
 * linlin@yiji.com 2016-10-13 14:41 创建
 *
 */
package com.global.com.global.common.service.lock.impl;

import com.global.com.global.common.service.lock.AbstractDistributedLock;
import com.global.com.global.common.service.lock.strategy.LockStrategy;
import com.global.com.global.common.service.lock.strategy.impl.YedisDistributedLockStrategy;
import com.yjf.common.log.Logger;
import com.yjf.common.log.LoggerFactory;

/**
 * @author linlin@yiji.com
 */
public class YedisDistributedLock extends AbstractDistributedLock {
	
	protected final Logger	logger	= LoggerFactory.getLogger(getClass());
									
	private LockStrategy	lockStrategy;
							
	public YedisDistributedLock(Object lockObj, int retryTimes, long expireMsecs) {
		super(lockObj, retryTimes, expireMsecs);
		lockStrategy = new YedisDistributedLockStrategy(retryTimes);
		setLockStrategy(lockStrategy);
	}
}
