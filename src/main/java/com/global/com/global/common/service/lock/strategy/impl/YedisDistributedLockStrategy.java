/*
 * www.yiji.com Inc.
 * Copyright (c) 2014 All Rights Reserved
 */

/*
 * 修订记录:
 * linlin@yiji.com 2016-11-09 09:44 创建
 *
 */
package com.global.com.global.common.service.lock.strategy.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.util.Assert;

import com.global.com.global.common.service.lock.strategy.LockStrategy;
import com.global.com.global.common.service.spring.SpringApplicationContextHolder;
import com.yjf.common.log.Logger;
import com.yjf.common.log.LoggerFactory;

/**
 * @author linlin@yiji.com
 * @see SpringApplicationContextHolder
 */
public class YedisDistributedLockStrategy implements LockStrategy {
	
	protected final Logger					logger		= LoggerFactory.getLogger(getClass());
	private static long						SLEEP_TIME	= 1000;
														
	@Autowired
	private RedisTemplate<Object, String>	redisTemplate;
											
	private int								reTryTimes	= 1;
														
	public YedisDistributedLockStrategy() {
		SpringApplicationContextHolder.get().getAutowireCapableBeanFactory()
			.autowireBeanProperties(this, AutowireCapableBeanFactory.AUTOWIRE_BY_NAME, false);
	}
	
	public YedisDistributedLockStrategy(int reTryTimes) {
		this();
		this.reTryTimes = reTryTimes;
		
	}
	
	public boolean lock(Object lockObj, long expireMsecs) {
		Assert.notNull(lockObj, "锁对象不能为空");
		ValueOperations va = redisTemplate.opsForValue();
		while (reTryTimes > -1) {
			long expires = System.currentTimeMillis() + expireMsecs + 1;
			String expiresStr = String.valueOf(expires); //锁到期时间
			if (va.setIfAbsent(lockObj.toString(), expiresStr)) {
				logger.info("获取锁成功key={}", lockObj);
				return true;
			}
			String currentValueStr = va.get(lockObj.toString()).toString(); //redis里的时间
			//检查是否超时
			if (currentValueStr != null
				&& Long.parseLong(currentValueStr) < System.currentTimeMillis()) {
				//判断是否为空，不为空的情况下，如果被其他线程设置了值，则第二个条件判断是过不去的
				//va.getAndSet是同步的
				String oldValueStr = va.getAndSet(lockObj.toString(), expiresStr).toString();
				if (oldValueStr != null && oldValueStr.equals(currentValueStr)) {
					//如过这个时候，多个线程恰好都到了这里，但是只有一个线程的设置值和当前值相同，他才有权利获取锁
					return true;
				}
			}
			reTryTimes--;
			try {
				Thread.sleep(SLEEP_TIME);
			} catch (InterruptedException e) {
				//忽略异常
			}
		}
		
		logger.info("获取锁失败key={}", lockObj);
		return false;
	}
	
	@Override
	public boolean unlock(Object lockObj) {
		Assert.notNull(lockObj, "锁对象不能为空");
		ValueOperations va = redisTemplate.opsForValue();
		if (va.getOperations().hasKey(lockObj.toString())) {
			va.getOperations().delete(lockObj.toString());
		}
		logger.info("释放锁成功key={}", lockObj);
		return true;
	}
}
