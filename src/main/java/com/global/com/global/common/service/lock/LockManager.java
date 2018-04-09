/*
 * www.yiji.com Inc.
 * Copyright (c) 2014 All Rights Reserved
 */

/*
 * 修订记录:
 * linlin@yiji.com 2016-10-28 10:33 创建
 *
 */
package com.global.com.global.common.service.lock;

import com.global.com.global.common.service.lock.exception.LockException;
import com.global.com.global.common.service.lock.impl.YedisDistributedLock;

/**
 * @author linlin@yiji.com
 */
public class LockManager {
	
	private static final int DEFAULT_RETRY_TIMES = 100;
	
	public static Object lockWith(Object lockObj, Operation operation) {
		Lock currentLock = retryLock(lockObj, 0);
		return doWithLock(currentLock, operation);
	}
	
	public static Object reTryLockWith(Object lockObj, Operation operation) {
		Lock currentLock = retryLock(lockObj, DEFAULT_RETRY_TIMES);
		return doWithLock(currentLock, operation);
	}
	
	public static Object reTryLockWith(Object lockObj, int reTryTimes, Operation operation) {
		Lock currentLock = retryLock(lockObj, reTryTimes);
		return doWithLock(currentLock, operation);
	}
	
	private static Object doWithLock(Lock currentLock, Operation operation) {
		boolean getLock = currentLock.lock();
		try {
			if (getLock) {
				return operation.doOperation();
			} else {
				throw new LockException("获取锁失败key");
			}
		} finally {
			if (getLock) {
				currentLock.unlock();
			}
		}
	}
	
	public static Lock retryLock(Object lockObj, int retryTimes) {
		return new YedisDistributedLock(lockObj, retryTimes, 30 * 60 * 1000);
	}
	
	@FunctionalInterface
	public interface Operation {
		Object doOperation();
	}
}
