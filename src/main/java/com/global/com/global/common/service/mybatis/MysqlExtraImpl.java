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

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author linlin@yiji.com
 */
public class MysqlExtraImpl extends AbstractExtraImpl {
	
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public long newSequence(String name) {
		ExtraDO dbDo = new ExtraDO();
		dbDo.setName(name);
		sqlSessionTemplate.insert("com.global.com.global.common.service.mybatis.ExtraMapper.MYSQL-SEQ-NEXTVAL", dbDo);
		return dbDo.getId();
	}
}
