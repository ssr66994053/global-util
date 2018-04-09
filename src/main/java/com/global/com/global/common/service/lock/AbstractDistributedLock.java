/*
 * www.yiji.com Inc.
 * Copyright (c) 2014 All Rights Reserved
 */

/*
 * 修订记录:
 * linlin@yiji.com 2016-11-08 10:31 创建
 *
 */
package com.global.com.global.common.service.lock;

import org.springframework.util.Assert;

import com.global.com.global.common.service.lock.strategy.LockStrategy;

/**
 * @author linlin@yiji.com
 */
public abstract class AbstractDistributedLock implements DistributedLock {
	
	//获取锁失败 重试次数 没认不重试，直接失败
	private int				retryTimes	= 0;
										
	//锁超时时间
	private long			expireMsecs	= 30 * 60 * 1000;
										
	//锁对象
	private Object			lockObj;
							
	//策略
	private LockStrategy	lockStrategy;
							
	public AbstractDistributedLock(Object lockObj) {
		this.lockObj = lockObj;
		Assert.notNull(getLockObj(), "锁对象不能为空");
	}
	
	public AbstractDistributedLock(Object lockObj, int retryTimes, long expireMsecs) {
		this.lockObj = lockObj;
		this.retryTimes = retryTimes;
		this.expireMsecs = expireMsecs;
		Assert.notNull(getLockObj(), "锁对象不能为空");
	}
	
	@Override
	public boolean lock() {
		Assert.notNull(getLockObj(), "锁对象不能为空");
		Assert.notNull(getLockStrategy(), "锁策略不能为空");
		return lockStrategy.lock(getLockObj(), getExpireMsecs());
	}
	
	@Override
	public boolean unlock() {
		Assert.notNull(getLockObj(), "锁对象不能为空");
		Assert.notNull(getLockStrategy(), "锁策略不能为空");
		return lockStrategy.unlock(getLockObj());
	}
	
	public int getRetryTimes() {
		return retryTimes;
	}
	
	public void setRetryTimes(int retryTimes) {
		this.retryTimes = retryTimes;
	}
	
	public long getExpireMsecs() {
		return expireMsecs;
	}
	
	public void setExpireMsecs(long expireMsecs) {
		this.expireMsecs = expireMsecs;
	}
	
	public Object getLockObj() {
		return lockObj;
	}
	
	public void setLockObj(Object lockObj) {
		this.lockObj = lockObj;
	}
	
	public LockStrategy getLockStrategy() {
		return lockStrategy;
	}
	
	public void setLockStrategy(LockStrategy lockStrategy) {
		this.lockStrategy = lockStrategy;
	}
	
}
