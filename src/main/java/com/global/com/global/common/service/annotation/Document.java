/*
 * www.yiji.com Inc.
 * Copyright (c) 2014 All Rights Reserved
 */

/*
 * 修订记录:
 * linlin@yiji.com 2017-04-26 15:38 创建
 *
 */
package com.global.com.global.common.service.annotation;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.SOURCE;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * @author linlin@yiji.com
 */
@Target({ METHOD, TYPE, FIELD, PARAMETER })
@Retention(SOURCE)
@Documented
public @interface Document {
	/**
	 * 名称
	 *
	 * @return
	 */
	String name();
	
	/**
	 * 字段或者方法的别名(文档参数）
	 *
	 * @return
	 */
	String alias() default "";
	
	/**
	 * 描述
	 *
	 * @return
	 */
	String desc();
	
	/**
	 * 示例
	 *
	 * @return
	 */
	String demo() default "";
	
	/**
	 * 默认值
	 *
	 * @return
	 */
	String defaultValue() default "";
}
