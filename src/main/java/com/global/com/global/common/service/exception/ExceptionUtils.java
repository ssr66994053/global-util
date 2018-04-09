package com.global.com.global.common.service.exception;

import com.yiji.adk.common.exception.BizException;
import com.yjf.common.lang.enums.CommonErrorCode;
import com.yjf.common.lang.result.Status;

/**
 * Created by Glory on 2016-07-26.
 */
public class ExceptionUtils {
	
	public static void throwNewNoRollbackException(BizException e) throws BizException {
		
		throw new NoRollbackException(e.getMessage(), e.getErrorCode(), e.getStatus(), null);
	}
	
	public static void throwNewNoRollbackException(Status status, String message) {
		throw new BizException(message, status) {
			@Override
			protected String defaultErrorCode() {
				return CommonErrorCode.SYSTEM_ERROR.code();
			}
		};
	}
	
	public static void throwNewGlobalBizException(	Status status,
													CommonErrorCode errorCode) throws BizException {
													
		throwNewGlobalBizException(status, errorCode, null);
	}
	
	public static void throwNewGlobalBizException(	Status status, final String remoteSystemErrorCode,
													String remoteSystemErrorMessage,
													Throwable e) throws BizException {
		throw new BizException(remoteSystemErrorMessage, null, status, e) {
			@Override
			protected String defaultErrorCode() {
				return remoteSystemErrorCode;
			}
		};
	}
	
	public static void throwNewGlobalBizException(	Status status, final CommonErrorCode errorCode,
													Throwable e) throws BizException {
		throw new BizException(errorCode.getMessage(), null, status, e) {
			@Override
			protected String defaultErrorCode() {
				return errorCode.code();
			}
		};
	}
}
