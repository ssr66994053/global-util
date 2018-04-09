/*
 * www.yiji.com Inc.
 * Copyright (c) 2014 All Rights Reserved
 */

/*
 * 修订记录:
 * linlin@yiji.com 2016-10-13 14:39 创建
 *
 */
package com.global.com.global.common.service.lock;

/**
 * @author linlin@yiji.com
 */
public interface DistributedLock extends Lock {
	
	boolean lock();
	
	boolean unlock();
}
