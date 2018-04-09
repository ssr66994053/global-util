/*
 * www.yiji.com Inc.
 * Copyright (c) 2014 All Rights Reserved
 */

/*
 * 修订记录:
 * linlin@yiji.com 2017-04-27 09:50 创建
 *
 */
package com.global.com.global.common.service.mybatis.handler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import com.yjf.common.payengine.enums.CurrencyEnum;

/**
 * @author linlin@yiji.com
 */
public class CurrencyEnumTypeHandler extends BaseTypeHandler<CurrencyEnum> {
	public void setNonNullParameter(PreparedStatement preparedStatement, int i, CurrencyEnum anEnum,
									JdbcType jdbcType) throws SQLException {
		preparedStatement.setString(i, anEnum.getCode());
	}
	
	public CurrencyEnum getNullableResult(ResultSet resultSet, String s) throws SQLException {
		String result = resultSet.getString(s);
		return CurrencyEnum.getByCode(result);
	}
	
	public CurrencyEnum getNullableResult(ResultSet resultSet, int i) throws SQLException {
		String result = resultSet.getString(i);
		return CurrencyEnum.getByCode(result);
	}
	
	public CurrencyEnum getNullableResult(	CallableStatement callableStatement,
											int i) throws SQLException {
		String result = callableStatement.getString(i);
		return CurrencyEnum.getByCode(result);
	}
}
