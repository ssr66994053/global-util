/*
 * www.yiji.com Inc.
 * Copyright (c) 2014 All Rights Reserved
 */

/*
 * 修订记录:
 * linlin@yiji.com 2017-04-27 17:07 创建
 *
 */
package com.global.com.global.common.service.mybatis;

import java.util.Map;

import com.google.common.collect.Maps;

/**
 * @author linlin@yiji.com
 */
public class OracleExtraImpl extends AbstractExtraImpl {
	
	/**
	 * 获取新序列
	 *
	 * @param name
	 * @return
	 */
	public long newSequence(String name) {
		Map<String, Object> params = Maps.newHashMap();
		params.put("name", name);
		params.put("seq_name", name);
		return sqlSessionTemplate.selectOne("ORACLE-SEQ-NEXTVAL", params);
	}
}
