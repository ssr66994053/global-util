/*
 * www.yiji.com Inc.
 * Copyright (c) 2014 All Rights Reserved
 */

/*
 * 修订记录:
 * linlin@yiji.com 2017-04-26 17:24 创建
 *
 */
package com.global.com.global.common.service;

import javax.management.Query;
import javax.validation.constraints.Min;

import com.global.com.global.common.service.annotation.Document;

/**
 * @author linlin@yiji.com
 */
@Document(name = "基础的可分页业务订单", desc = "基础的可分页业务订单，定义了分页参数")
public class AbstractPageableBizOrder extends AbstractBizOrder implements Pagable {
	//分页信息
	/**
	 * 当前页
	 */
	@Document(name = "页码", desc = "分页查询时的指定的页数", demo = "1", defaultValue = "1")
	@Min(value = 1, groups = { Query.class })
	protected int	pageNo		= 1;
								
	/**
	 * 每页大小
	 */
	@Document(name = "分页大小", desc = "分页查询时的指定分页大小(每页返回的记录数)", demo = "15", defaultValue = "15")
	@Min(value = 1, groups = { Query.class })
	protected int	pageSize	= PageInfo.DEFAULT_PAGE_SIZE;
								
	/**
	 * 返回 当前页码
	 */
	public int getPageNo() {
		return pageNo;
	}
	
	/**
	 * 设置 当前页码
	 */
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	
	/**
	 * 返回 分页大小
	 */
	public int getPageSize() {
		return pageSize;
	}
	
	/**
	 * 设置 分页大小
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	public void setPageInfo(PageInfo pageInfo) {
		if (pageInfo != null) {
			setPageNo(pageInfo.getPageNo());
			setPageSize(pageInfo.getPageSize());
		}
	}
	
	public PageInfo getPageInfo() {
		return new PageInfo(getPageNo(), getPageSize());
	}
}