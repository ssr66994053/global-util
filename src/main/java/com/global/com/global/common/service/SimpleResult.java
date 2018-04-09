/*
 * www.yiji.com Inc.
 * Copyright (c) 2014 All Rights Reserved
 */

/*
 * 修订记录:
 * linlin@yiji.com 2017-04-26 15:48 创建
 *
 */
package com.global.com.global.common.service;

import com.global.com.global.common.service.annotation.Document;
import com.global.com.global.common.service.enums.TranslateResultCode;
import com.yjf.common.lang.result.StandardResultInfo;
import com.yjf.common.lang.result.Status;
import com.yjf.common.util.StringUtils;

/**
 * @author linlin@yiji.com
 */
@Document(name = "抽象执行结果", desc = "简单执行结果，返回执行的状态")
public  class SimpleResult extends StandardResultInfo {
	
	/**
	 * 结果码描述信息
	 */
	@Document(name = "结果码描述信息", desc = "描述结果码的含义", demo = "执行成功")
	private String message;
	
	public String getMessage() {
		if (message == null && StringUtils.isNotBlank(super.getDescription())) {
			return super.getDescription();
		}
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
		if (StringUtils.isNotBlank(message) && StringUtils.isBlank(super.getDescription())) {
			setDescription(message);
		}
	}
	
	public void setResultCode(ResultCodeable resultCode) {
		setCode(resultCode.getCode());
		setMessage(resultCode.getMessage());
	}
	
	@Override
	public String getDescription() {
		if (StringUtils.isBlank(super.getDescription()) && StringUtils.isNotBlank(message)) {
			return message;
		}
		return super.getDescription();
	}
	
	/**
	 * 将结果设置为成功<br/>
	 * <p>
	 * 等价于
	 * <pre>
	 *  setStatus(Status.SUCCESS);
	 *  setToSuccess(CommonResultCode.EXECUTE_SUCCESS)
	 *  </pre>
	 */
	public void setToSuccess() {
		setToSuccess(TranslateResultCode.EXECUTE_SUCCESS);
	}
	
	/**
	 * 将结果设置为成功<br/>
	 * <p>
	 * 等价于
	 * <pre>
	 *  setStatus(Status.SUCCESS);
	 *  setToSuccess(CommonResultCode.EXECUTE_SUCCESS)
	 *  setDescription(description)
	 *  </pre>
	 */
	public void setToSuccess(String description) {
		setToSuccess(TranslateResultCode.EXECUTE_SUCCESS, description);
	}
	
	/**
	 * 将结果设置为成功<br/>
	 * <p>
	 * 等价于
	 * <pre>
	 *  setStatus(Status.SUCCESS);
	 *  setToSuccess(resultCode)
	 *  </pre>
	 */
	public void setToSuccess(ResultCodeable resultCode) {
		setStatus(Status.SUCCESS);
		setResultCode(resultCode);
	}
	
	/**
	 * 将结果设置为成功<br/>
	 * <p>
	 * 等价于
	 * <pre>
	 *  setStatus(Status.SUCCESS);
	 *  setResultCode(resultCode)
	 *  setDescription(description)
	 *  </pre>
	 */
	public void setToSuccess(ResultCodeable resultCode, String description) {
		setStatus(Status.SUCCESS);
		setResultCode(resultCode);
		setDescription(description);
	}
	
	/**
	 * 将结果设置为失败<br/>
	 * <p>
	 * 等价于
	 * <pre>
	 *  setStatus(Status.FAIL);
	 *  setResultCode(resultCode)
	 *  </pre>
	 */
	public void setToFail(ResultCodeable resultCode) {
		setStatus(Status.FAIL);
		setResultCode(resultCode);
	}
	
	/**
	 * 将结果设置为失败<br/>
	 * <p>
	 * 等价于
	 * <pre>
	 *  setStatus(Status.FAIL);
	 *  setResultCode(resultCode)
	 *  setDescription(description)
	 *
	 *  </pre>
	 */
	public void setToFail(ResultCodeable resultCode, String description) {
		setStatus(Status.FAIL);
		setResultCode(resultCode);
		setDescription(description);
	}
	
	/**
	 * 将结果设置为处理中<br/>
	 * <p>
	 * 等价于
	 * <pre>
	 *  setStatus(Status.FAIL);
	 *  setStatus(CommonResultCode.RUNNING_IN_PROCESS)
	 *
	 *  </pre>
	 */
	public void setToProcessing() {
		setStatus(Status.PROCESSING);
		setResultCode(TranslateResultCode.RUNNING_IN_PROCESS);
	}
	
	/**
	 * 将结果设置为处理中<br/>
	 * <p>
	 * 等价于
	 * <pre>
	 *  setStatus(Status.FAIL);
	 *  setStatus(CommonResultCode.RUNNING_IN_PROCESS)
	 *  setDescription(description)
	 *
	 *  </pre>
	 */
	public void setToProcessing(String description) {
		setStatus(Status.PROCESSING);
		setResultCode(TranslateResultCode.RUNNING_IN_PROCESS);
		setDescription(description);
	}
}
