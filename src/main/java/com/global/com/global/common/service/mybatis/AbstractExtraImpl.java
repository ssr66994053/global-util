/*
 * www.yiji.com Inc.
 * Copyright (c) 2014 All Rights Reserved
 */

/*
 * 修订记录:
 * linlin@yiji.com 2017-04-27 17:05 创建
 *
 */
package com.global.com.global.common.service.mybatis;

import java.util.Date;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.lang.StringUtils;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Maps;
import com.yjf.common.lang.util.DateUtil;

/**
 * @author linlin@yiji.com
 */
public abstract class AbstractExtraImpl implements ExtraMapper {
	/**
	 * 同步数据库的时间
	 */
	public static final long			UPDATE_INTERVAL_TIME	= 10 * 60 * 1000;
																
	public static int					default_id_length		= 20;
																
	//创建session连接实例
	@Autowired
	protected SqlSessionTemplate		sqlSessionTemplate;
										
	/**
	 * 实体缓存
	 */
	private volatile long				timeInterval			= 0;
	private volatile long				lastUpdateTime			= -1;
																
	private volatile Map<Key, String>	seqFlagMap				= Maps.newConcurrentMap();
																
	@PostConstruct
	private void init() {
		getSysdate();
	}
	
	/**
	 * 获取数据库时间
	 *
	 * @return
	 */
	public Date getSysdate() {
		return getSysdate(0);
	}
	
	public Date getSysdate(long delay) {
		final long appTime = DateUtil.now().getTime();
		if (appTime - lastUpdateTime > UPDATE_INTERVAL_TIME) {
			timeInterval = getSysdate1().getTime() - new Date().getTime();
			lastUpdateTime = appTime;
		}
		Date sysDate = new Date(appTime + timeInterval + delay);
		return sysDate;
	}
	
	@Override
	public String newId(String seqName, String bizCode) {
		return newId(seqName, bizCode, null);
	}
	
	@Override
	public String newId(String seqName, String bizCode, int len) {
		return newId(seqName, bizCode, null, len);
	}
	
	@Override
	public String newId(String seqName, String bizCode, String dbCode) {
		return newId(seqName, bizCode, dbCode, default_id_length);
	}
	
	@Override
	public String newId(String seqName, String bizCode, String dbCode, int len) {
		String sysSeq = DateUtil.shortDate(getSysdate()) + getSeqFlag(bizCode, dbCode);
		long dbSeq = newSequence(seqName);
		String dbSeqsString = StringUtils.leftPad(String.valueOf(dbSeq), len - 8, "0");
		return sysSeq + StringUtils.right(dbSeqsString, len - sysSeq.length());
	}
	
	private String getSeqFlag(String bizCode, String dbCode) {
		Key key = new Key(bizCode, dbCode);
		String sqlFlag = seqFlagMap.get(key);
		if (sqlFlag == null) {
			sqlFlag = (bizCode == null ? "" : StringUtils.leftPad(bizCode, 3, '0'))
						+ (dbCode == null ? "" : StringUtils.leftPad(dbCode, 2, '0'));
			seqFlagMap.put(key, sqlFlag);
		}
		return sqlFlag;
	}
	
	private Date getSysdate1() {
		return sqlSessionTemplate.selectOne("com.global.com.global.common.service.mybatis.ExtraMapper.COMMON-GET-SYSDATE");
	}
	
	private class Key {
		private String	bizCode;
		private String	dbCode;
						
		public Key(String bizCode, String dbCode) {
			this.bizCode = bizCode;
			this.dbCode = dbCode;
		}
		
		@Override
		public boolean equals(Object o) {
			if (this == o)
				return true;
			if (o == null || getClass() != o.getClass())
				return false;
				
			Key key = (Key) o;
			
			if (bizCode != null ? !bizCode.equals(key.bizCode) : key.bizCode != null)
				return false;
			return dbCode != null ? dbCode.equals(key.dbCode) : key.dbCode == null;
			
		}
		
		@Override
		public int hashCode() {
			int result = bizCode != null ? bizCode.hashCode() : 0;
			result = 31 * result + (dbCode != null ? dbCode.hashCode() : 0);
			return result;
		}
	}
}
