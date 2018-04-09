/*
 * www.yiji.com Inc.
 * Copyright (c) 2014 All Rights Reserved
 */

/*
 * 修订记录:
 * linlin@yiji.com 2016-10-28 09:46 创建
 *
 */
package com.global.com.global.common.service.lock;

/**
 * @author linlin@yiji.com
 */
public interface Lock {
	
	boolean lock();
	
	boolean unlock();
}
