/*
 * www.yiji.com Inc.
 * Copyright (c) 2014 All Rights Reserved
 */

/*
 * 修订记录:
 * linlin@yiji.com 2016-10-13 14:32 创建
 *
 */
package com.global.com.global.common.service.aop;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.global.com.global.common.service.lock.LockManager;
import com.yjf.common.log.Logger;
import com.yjf.common.log.LoggerFactory;
import com.yjf.common.util.StringUtils;

/**
 * @author linlin@yiji.com
 *         分布式锁切面，实现加锁 释放锁的功能
 */
@Component
@Aspect
public class DistributedLockAspect {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Pointcut("execution(@com.global.com.global.common.service.annotation.DistributedLock * *(..))")
	public void pointCut() {
	}
	
	@Around("pointCut()")
	public Object around(final ProceedingJoinPoint joinPoint) {
		
		logger.info("claz={}", joinPoint.getTarget().getClass());
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		final Method method = getMethod(joinPoint);
		com.global.com.global.common.service.annotation.DistributedLock a = method
			.getAnnotation(com.global.com.global.common.service.annotation.DistributedLock.class);
		String methodName = joinPoint.getTarget().getClass().getName() + "." + method.getName();
		String value;
		if (null != a && StringUtils.isNotBlank(a.value().trim())) {
			value = convertValueIfRequired(a.value().trim());
		} else {
			value = methodName;
		}
		if (null != signature.getParameterNames() && signature.getParameterNames().length > 0) {
			EvaluationContext context = buildContext(signature.getParameterNames(),
				joinPoint.getArgs());
			ExpressionParser parser = new SpelExpressionParser();
			value = parser.parseExpression(value).getValue(context, String.class);
		}
		return LockManager.lockWith(value, new LockManager.Operation() {
			@Override
			public Object doOperation() {
				try {
					return joinPoint.proceed();
				} catch (Throwable throwable) {
					logger.error("方法：{}出错", method, throwable);
					return null;
				}
			}
		});
	}
	
	private Method getMethod(JoinPoint joinPoint) {
		try {
			return joinPoint.getTarget().getClass().getMethod(
				((MethodSignature) joinPoint.getSignature()).getMethod().getName(),
				((MethodSignature) joinPoint.getSignature()).getParameterTypes());
		} catch (NoSuchMethodException e) {
			logger.error("没有方法，AOP出错", e);
			throw new RuntimeException(e);
		}
	}
	
	private static EvaluationContext buildContext(String[] paramNames, Object[] paramValues) {
		Assert.isTrue(paramNames.length == paramValues.length);
		EvaluationContext context = new StandardEvaluationContext();
		for (int i = 0; i < paramNames.length; i++) {
			if (null !=paramValues[i]) {
				context.setVariable(paramNames[i], paramValues[i].toString());
				context.setVariable(String.valueOf(i), paramValues[i].toString());
			} else {
				context.setVariable(paramNames[i], "null");
			}
		}
		return context;
		
	}
	
	private static String convertValueIfRequired(String s) {
		String newString = s;
		if (!newString.startsWith("#")) {
			newString = "'" + newString;
			newString = newString + "'";
		}
		newString = newString.replace("#{", "'+#").replace("}", "+'");
		if (newString.startsWith("'+")) {
			newString = newString.substring(2, s.length());
			newString = newString + "'";
		}
		if (newString.endsWith("''")) {
			newString = newString.substring(0, newString.lastIndexOf('+'));
		}
		return newString;
	}
	
}
