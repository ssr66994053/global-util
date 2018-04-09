/*
 * www.yiji.com Inc.
 * Copyright (c) 2014 All Rights Reserved
 */

/*
 * 修订记录:
 * linlin@yiji.com 2017-04-26 17:08 创建
 *
 */
package com.global.com.global.common.service;

import com.global.com.global.common.service.annotation.Document;

/**
 * @author linlin@yiji.com
 */
@Document(name = "可分页的",desc = "实现该接口即表示对象有分页信息")
public interface Pagable {
    /**
     * 获取分页信息
     *
     * @return
     */
    @Document(name = "返回分页信息",desc = "返回分页信息")
     PageInfo getPageInfo();
}
