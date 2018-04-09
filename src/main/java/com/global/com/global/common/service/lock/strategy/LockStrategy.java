/*
 * www.yiji.com Inc.
 * Copyright (c) 2014 All Rights Reserved
 */

/*
 * 修订记录:
 * linlin@yiji.com 2016-11-09 09:42 创建
 *
 */
package com.global.com.global.common.service.lock.strategy;

/**
 * @author linlin@yiji.com
 */
public interface LockStrategy {
	
	boolean lock(Object lockObj, long expireMsecs);
	
	boolean unlock(Object lockObj);
}
