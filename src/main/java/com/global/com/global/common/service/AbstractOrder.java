/*
 * www.yiji.com Inc.
 * Copyright (c) 2014 All Rights Reserved
 */

/*
 * 修订记录:
 * linlin@yiji.com 2017-04-26 15:07 创建
 *
 */
package com.global.com.global.common.service;

import java.util.Iterator;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.global.com.global.common.service.annotation.Document;
import com.yjf.common.lang.validator.YJFValidatorFactory;
import com.yjf.common.service.Order;
import com.yjf.common.service.OrderCheckException;
import com.yjf.common.service.Validatable;
import com.yjf.common.util.ToString;

/**
 * @author linlin@yiji.com
 * 定义了GID规范，同一笔业务在
 * 各个系统之间可以由GID标识
 * 所有系统间的交付都由此类扩展
 */
@Document(name = "抽象的Order", desc = "接口定义了GID一个参数(规范)，且实现了校验Order的方法")
public abstract class AbstractOrder implements Order, Validatable {
	private static final long	serialVersionUID	= 5887885132084755390L;
	@NotNull
	@Length(min = 35, max = 35)
	/**gid 为了保证gid全局唯一,请使用 {@link com.yjf.common.id.GID#newGID()*/
	@Document(name = "全局统一的流水号", desc = "全局统一的流水号，由入口系统(Api、Boss)生成", demo = "00050000430000000001704251023220000")
	private String				gid;
								
	public AbstractOrder() {
	}
	
	@Override
	public String getGid() {
		return this.gid;
	}
	
	@Override
	public void setGid(String gid) {
		this.gid = gid;
	}
	
	@Override
	public void check() {
		Set constraintViolations = YJFValidatorFactory.INSTANCE.getValidator().validate(this);
		this.validate(constraintViolations);
	}
	
	@Override
	public void checkWithGroup(Class<?>... groups) {
		check();
		if (null != groups && groups.length != 0) {
			Set constraintViolations = YJFValidatorFactory.INSTANCE.getValidator().validate(this,
				groups);
			this.validate(constraintViolations);
		}
		
	}
	
	protected <T> void validate(Set<ConstraintViolation<T>> constraintViolations) {
		OrderCheckException exception = null;
		if (constraintViolations != null && !constraintViolations.isEmpty()) {
			exception = new OrderCheckException();
			Iterator i$ = constraintViolations.iterator();
			
			while (i$.hasNext()) {
				ConstraintViolation constraintViolation = (ConstraintViolation) i$.next();
				exception.addError(constraintViolation.getPropertyPath().toString(),
					constraintViolation.getMessage());
			}
		}
		
		if (exception != null) {
			throw exception;
		}
	}
	
	public String toString() {
		return ToString.toString(this);
	}
}
