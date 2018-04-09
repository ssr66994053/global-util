/*
 * www.yiji.com Inc.
 * Copyright (c) 2014 All Rights Reserved
 */

/*
 * 修订记录:
 * linlin@yiji.com 2016-10-28 10:26 创建
 *
 */
package com.global.com.global.common.service.lock;

/**
 * @author linlin@yiji.com
 */
public enum DistributedLockMediumType {
	
	ZOOKEEPER("zookeeper"), REDIS("redis");
	private String value;
	
	DistributedLockMediumType(String value) {
		this.value = value;
	}
}
