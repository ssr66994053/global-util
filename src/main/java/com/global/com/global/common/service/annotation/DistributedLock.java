/*
 * www.yiji.com Inc.
 * Copyright (c) 2014 All Rights Reserved
 */

/*
 * 修订记录:
 * linlin@yiji.com 2016-10-13 14:29 创建
 *
 */
package com.global.com.global.common.service.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author linlin@yiji.com
 * 分布式锁
 * 加上此注解的方法会锁住当前操作
 * 支持 方法参数做为同步参数
 * 如 <code>@DistributedLock(value="com.yiji.xxx.xxx#{id}")</code>
 * id为参数被注解方法的形式参数名称
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DistributedLock {
	
	String value() default "";
}
