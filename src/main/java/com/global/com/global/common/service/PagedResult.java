/*
 * www.yiji.com Inc.
 * Copyright (c) 2014 All Rights Reserved
 */

/*
 * 修订记录:
 * linlin@yiji.com 2017-04-26 17:07 创建
 *
 */
package com.global.com.global.common.service;

import java.util.List;

import com.google.common.collect.Lists;

/**
 * @author linlin@yiji.com
 */
public class PagedResult<T> extends SimpleResult implements Pagable {
	/**
	 * 结果信息列表
	 */
	private List<T>		infoList;
						
	/**
	 * 分页信息
	 */
	private PageInfo	pageInfo;
						
	public static <T> PagedResult<T> newInstance() {
		return new PagedResult<>();
	}
	
	/**
	 * 结果信息列表<br/>
	 * 仿照Mybatis查询，如果没有数据，返回空数组</>
	 *
	 * @return
	 */
	public List<T> getInfoList() {
		if (infoList == null) {
			infoList = Lists.newArrayList();
		}
		return infoList;
	}
	
	/**
	 * 结果信息列表
	 *
	 * @param infoList
	 */
	public void setInfoList(List<T> infoList) {
		this.infoList = infoList;
	}
	
	public void addInfo(T info) {
		this.getInfoList().add(info);
	}
	
	/**
	 * 分页信息
	 *
	 * @param pageInfo
	 */
	public void setPageInfo(PageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}
	
	/**
	 * 分页信息
	 *
	 * @return
	 */
	@Override
	public PageInfo getPageInfo() {
		return pageInfo;
	}
	
	/**
	 * 返回结果列表中的记录条数
	 *
	 * @return
	 */
	public int getInfoSize() {
		return getInfoList().size();
	}
	
	/**
	 * 记录总条数
	 *
	 * @return
	 */
	public long getTotalCount() {
		return pageInfo == null ? getInfoSize() : pageInfo.getTotalCount();
	}
	
	public PageInfo resolvePageInfo() {
		if (getPageInfo() == null) {
			setPageInfo(new PageInfo());
		}
		return getPageInfo();
	}
	
	public void setPageInfo(int pageNo, int pageSize, long totalCount) {
		PageInfo pageInfo = resolvePageInfo();
		pageInfo.setPageNo(pageNo);
		pageInfo.setPageSize(pageSize);
		pageInfo.setTotalCount(totalCount);
	}
}
