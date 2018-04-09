/*
 * www.yiji.com Inc.
 * Copyright (c) 2014 All Rights Reserved
 */

/*
 * 修订记录:
 * linlin@yiji.com 2017-04-26 17:09 创建
 *
 */
package com.global.com.global.common.service;

import com.global.com.global.common.service.annotation.Document;
import com.yjf.common.lang.util.Paginator;
import com.yjf.common.util.ToString;

import java.io.Serializable;

/**
 * @author linlin@yiji.com
 */
@Document(name = "分页结构信息",desc = "分页查询或返回是的分页信息")
public class PageInfo  implements Serializable {
    private static final long	serialVersionUID	= -2275003133090835778L;

    public static int DEFAULT_PAGE_SIZE = 10;

    /**
     * 当前页码，1为 第1页
     */
    @Document(name = "页码",desc = "分页查询时的指定的页数",demo = "1",defaultValue = "1")
    protected int pageNo = 1;

    /**
     * 每页查询数量
     */
    @Document(name = "分页大小",desc = "分页查询时的指定分页大小(每页返回的记录数)",demo = "15",defaultValue = "15")
    protected int pageSize = DEFAULT_PAGE_SIZE;

    /**
     * 总数据
     */
    @Document(name = "记录总数",desc = "查询条件查询的所有记录数",demo = "123",defaultValue = "0")
    protected long totalCount = 0;

    public PageInfo() {

    }
    public PageInfo(int pageNo, int pageSize) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }

    public static PageInfo newInstance() {
        return new PageInfo();
    }

    /**
     * 修改默认分页大小
     *
     * @param pageSize
     */
    public static final void setDefaultPageSize(int pageSize) {
        DEFAULT_PAGE_SIZE = pageSize;
    }

    /**
     * 当前页码，1为 第1页
     *
     * @return
     */
    public int getPageNo() {
        return pageNo <= 0 ? 1 : pageNo;
    }

    /**
     * 当前页码，1为 第1页
     *
     * @param pageNo
     */
    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    /**
     * 每页查询数量，分页大小
     *
     * @return
     */
    public int getPageSize() {
        return pageSize <= 0 ? DEFAULT_PAGE_SIZE : pageSize;
    }

    /**
     * 每页查询数量，分页大小
     *
     * @param pageSize
     */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * 数据总条数
     *
     * @return
     */
    public long getTotalCount() {
        return totalCount;
    }

    /**
     * 数据总条数
     *
     * @param totalCount
     */
    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    /**
     * 起始数据
     *
     * @return
     */
    public long start() {
        return (getPageNo() - 1) * pageSize;
    }

    /**
     * 取得总页数。
     *
     * @return 总页数
     */
    public int getTotalPage() {
        return (int) Math.ceil((double) getTotalCount() / getPageSize());
    }

    /**
     * 转换为 yjf_common_util中的Paginator
     *
     * @return
     */
    public Paginator convertToPaginator() {
        Paginator paginator = new Paginator();
        // 每页项数。
        paginator.setItemsPerPage(getPageSize());
        // 当前页码。(1-based)
        paginator.setPage(getPageNo());
        // 总共项数
        paginator.setItems(new Long(getTotalCount()).intValue());

        return paginator;
    }

    /**
     * 将yjf_common_util中的Paginator转换PageInfo
     *
     * @param paginator
     */
    public void convertToFrom(Paginator paginator) {
        setTotalCount(paginator.getItems());
        setPageSize(paginator.getItemsPerPage());
        setPageNo(paginator.getPage());
    }

    @Override
    public String toString() {
        return ToString.toString(this);
    }
}
