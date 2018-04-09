/*
 * www.yiji.com Inc.
 * Copyright (c) 2014 All Rights Reserved
 */

/*
 * 修订记录:
 * linlin@yiji.com 2017-04-26 17:28 创建
 *
 */
package com.global.com.global.common.service.dubbo;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.context.ApplicationContext;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.config.ConsumerConfig;
import com.alibaba.dubbo.config.ProviderConfig;
import com.alibaba.dubbo.rpc.*;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.yjf.common.collection.CollectionUtils;
import com.yjf.common.lang.context.OperationContext;
import com.yjf.common.service.Order;
import com.yjf.common.spring.ApplicationContextHolder;
import com.yjf.common.util.StringUtils;
import com.yjf.common.util.ToString;

/*
 * 修订记录:
 * @author Mr.Wolffy (e-mail:zhouyang@yiji.com) 2016-11-09 12:04 创建
 *
 */

/**
 * dubbo 调用日志 <p/>
 * 启用方式:
 * <lu>
 *     <li>
 * 			1、在resources文件夹下新建文件 META-INF/dubbo/com.alibaba.dubbo.rpc.Filter
 *     </li>
 *     <li>
 *			2、文件中增加：dubboServiceInvokeFilter=com.cb.common.service.dubbo.InvokeLogFilter
 *     </li>
 * </lu>
 * @author Mr.Wolffy (e-mail:zhouyang@yiji.com)
 */
@Activate(group = { Constants.PROVIDER, Constants.CONSUMER })
public class InvokeLogFilter implements Filter {
	
	private static final Map<Class<?>, Logger>	loggerMap				= Maps.newConcurrentMap();
																		
	private static final AtomicLong				requestId				= new AtomicLong();
																		
	public static final String					OWNER_KEY				= "owner";
	public static final String					EXCLUDED_LOG_PAKAGES	= "cb.dubbo.logger.excluded";
	public static final String					EXCLUDED_LOG_ENABLE		= "cb.dubbo.logger.enable";
																		
	/**
	 * 是否已启用客户端日志
	 */
	private static Boolean						usedConsumerLog			= null;
	/**
	 * 是否已启用服务端日志
	 */
	private static Boolean						usedProviderLog			= null;
																		
	private static boolean						enable					= false;
																		
	private final static List<String>			excludedPakages			= Lists.newArrayList();
																		
	static {
		String enableStr = System.getProperty(EXCLUDED_LOG_ENABLE);
		if (StringUtils.equalsIgnoreCase(enableStr, "true")) {
			enable = true;
		}
		
		excludedPakages.add("com.alibaba.dubbo.*");
		String excludedLogPakages = System.getProperty(EXCLUDED_LOG_PAKAGES);
		if (StringUtils.isNotBlank(excludedLogPakages)) {
			String[] pkgs = excludedLogPakages.split(",");
			for (String pkg : pkgs) {
				excludedPakages.add(pkg);
			}
		}
	}
	
	@Override
	public Result invoke(Invoker<?> invoker, Invocation inv) throws RpcException {
		if (enable&& (!isUsedConsumerLog() || !isUsedProviderLog())
			&& !isExcludedInterface(invoker.getInterface())) {
			//未启用服务端日志或未启用客户端日志
			return doInvoke(invoker, inv);
		} else {
			return invoker.invoke(inv);
		}
	}
	
	private boolean isExcludedInterface(Class<?> anInterface) {
		String clazzName = anInterface.getName();
		Iterator<String> iterator = excludedPakages.iterator();
		while (iterator.hasNext()) {
			String pkg = iterator.next();
			try {
				if (clazzName.matches(pkg)) {
					return true;
				}
			} catch (Exception e) {
				iterator.remove();
			}
		}
		return false;
	}
	
	public Result doInvoke(Invoker<?> invoker, Invocation inv) throws RpcException {
		long id = requestId.getAndIncrement();
		long now = System.currentTimeMillis();
		boolean isProviderSide = false;
		try {
			
			RpcContext context = RpcContext.getContext();
			
			String serviceName = invoker.getInterface().getSimpleName();
			String version = invoker.getUrl().getParameter(Constants.VERSION_KEY);
			String group = invoker.getUrl().getParameter(Constants.GROUP_KEY);
			
			StringBuilder sn = new StringBuilder();
			if (null != group && group.length() > 0) {
				sn.append(group).append("/");
			}
			sn.append(serviceName);
			if (null != version && version.length() > 0) {
				sn.append(":").append(version);
			}
			sn.append("#");
			sn.append(inv.getMethodName());
			sn.append("(");
			Class<?>[] types = inv.getParameterTypes();
			if (types != null && types.length > 0) {
				boolean first = true;
				for (Class<?> type : types) {
					if (first) {
						first = false;
					} else {
						sn.append(",");
					}
					sn.append(type.getSimpleName());
				}
			}
			sn.append(") ");
			String address = context.getRemoteHost() + ":" + context.getRemotePort();
			
			String msg = sn.toString();
			if (context.isProviderSide()) {
				isProviderSide = true;
				if (!isUsedProviderLog()) {
					//如果未启用服务端日志
					setLogKey(inv.getArguments());
					info(invoker.getInterface(), "==>>>>>>收到来至[{}]的[DUBBO-{}]请求:{}", address, id,
						msg);
					logRequestParams(invoker.getInterface(), inv.getArguments());
				}
			} else if (context.isConsumerSide()) {
				if (!isUsedConsumerLog()) {
					//如果未启用客户端日志
					String owner = inv.getInvoker().getUrl().getParameter(OWNER_KEY);
					//				String organization = inv.getInvoker ().getUrl ().getParameter (OWNER_ORGANIZATION);
					String charge = owner;
					//				if (StringUtils.isNotEmpty (organization)) {
					//					charge = "(" + organization + ")";
					//				}
					//				charge = charge + owner;
					
					info(invoker.getInterface(), ">>调用[{}]的[DUBBO-{}]服务:{}  负责人:{}", address, id,
						msg, charge);
					logRequestParams(invoker.getInterface(), inv.getArguments());
				}
			} else {
				info(invoker.getInterface(), "[DUBBO-{}]请求:{}  {}", id, msg, address);
			}
			//			logger.info ("dubbo url={}",context.getUrl ());
		} catch (Throwable t) {
			info(getClass(),
				"Exception in ConsumerRequestLogFilter of service(" + invoker + " -> " + inv + ")",
				t);
		}
		
		Result result = invoker.invoke(inv);
		
		logResponse(invoker.getInterface(), result);
		if (isProviderSide) {
			if (!isUsedProviderLog()) {
				info(invoker.getInterface(), "<<<<<<==处理耗时:{}ms\n\n",
					System.currentTimeMillis() - now);
			}
		} else {
			if (!isUsedConsumerLog()) {
				info(invoker.getInterface(), "<<耗时:{}ms", System.currentTimeMillis() - now);
			}
			LoggerMDCUtils.clear();
		}
		return result;
	}
	
	protected boolean isUsedProviderLog() {
		if (usedProviderLog == null) {
			ApplicationContext applicationContext = ApplicationContextHolder.get();
			if (applicationContext != null) {
				Map<String, ProviderConfig> configMap = applicationContext
					.getBeansOfType(ProviderConfig.class);
				if (CollectionUtils.isNotEmpty(configMap)) {
					usedProviderLog = false;
					for (Map.Entry<String, ProviderConfig> config : configMap.entrySet()) {
						if (StringUtils.isNotBlank(config.getValue().getFilter())) {
							usedProviderLog = true;
							break;
						}
					}
				}
			}
		}
		return usedProviderLog == null ? false : usedProviderLog;
	}
	
	protected boolean isUsedConsumerLog() {
		if (usedConsumerLog == null) {
			ApplicationContext applicationContext = ApplicationContextHolder.get();
			if (applicationContext != null) {
				Map<String, ConsumerConfig> configMap = applicationContext
					.getBeansOfType(ConsumerConfig.class);
				if (CollectionUtils.isNotEmpty(configMap)) {
					usedConsumerLog = false;
					for (Map.Entry<String, ConsumerConfig> config : configMap.entrySet()) {
						if (StringUtils.isNotBlank(config.getValue().getFilter())) {
							usedProviderLog = true;
							break;
						}
					}
				}
			}
		}
		return usedConsumerLog == null ? false : usedConsumerLog;
	}
	
	protected void setLogKey(Object[] args) {
		if (args.length > 0) {
			Object arg = args[0];
			if (arg instanceof Order) {
				LoggerMDCUtils.setLogKey(((Order) arg).getGid());
			} else if (arg instanceof String) {
				LoggerMDCUtils.setLogKey(StringUtils.left(((String) arg), 36));
			} else {
				LoggerMDCUtils.clear();
			}
		}
	}
	
	protected void logRequestParams(Class<?> facade, Object[] args) {
		if (args.length > 0) {
			try {
				
				int i;
				StringBuilder infoBuilder = new StringBuilder();
				List<String> argStrList = Lists.newArrayList();
				for (i = 0; i < args.length; i++) {
					Object arg = args[i];
					if (arg == null) {
						infoBuilder.append(",arg").append(i).append("=null ");
					} else if (arg instanceof OperationContext) {
						break;
					} else if (arg instanceof Order) {
						infoBuilder.append(",order={}");
						argStrList.add(ToString.toString(arg));
					} else if (arg.getClass().isEnum()) {
						infoBuilder.append(",arg").append(i).append("={}");
						argStrList.add(arg.toString());
					} else {
						infoBuilder.append(",arg").append(i).append("={}");
						argStrList.add(ToString.toString(arg));
					}
				}
				if (i > 0 && !(args[0] instanceof OperationContext)) {
					infoBuilder.deleteCharAt(0);
					infoBuilder.insert(0, "调用参数:");
					info(facade, infoBuilder.toString(), argStrList.toArray());
				}
			} catch (Exception e) {
				info(facade, "打印请求参数日志失败", e);
			}
		}
	}
	
	protected void logResponse(Class<?> facade, Result result) {
		try {
			if (result.hasException()) {
				info(facade, "接口调用异常！", result.getException());
			} else {
				info(facade, "调用返回：result={}", result.getValue());
			}
		} catch (Exception e) {
			info(facade, "打印返回结果日志失败", e);
		}
		
	}
	
	protected void info(Class<?> facade, String info, Object... parms) {
		Logger logger = loggerMap.get(facade);
		if (logger == null) {
			logger = LoggerFactory.getLogger(facade);
			loggerMap.put(facade, logger);
		}
		logger.info(info, parms);
	}
	
	private static class LoggerMDCUtils {
		/**
		 * 使用logback mdc 特性，打印交易号或者其他可以作为用户请求凭证的信息，便于日志分析
		 */
		public static final String LOG_KEY_GID = "gid";
		
		public static void clear() {
			MDC.clear();
		}
		
		public static void setLogKey(String logKey) {
			clear();
			MDC.put(LOG_KEY_GID, logKey);
		}
	}
}
