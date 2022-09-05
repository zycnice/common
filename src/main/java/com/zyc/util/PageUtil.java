package com.zyc.util;

import lombok.Data;

import java.util.List;

/**
 * 分页信息
 */
@Data
public class PageUtil {
    /**
     *   每页记录条数
     */
    public static final Integer SIZE = 3;

    /**
     * 当前第几页
     */
    private Integer page;
    /**
     * 一共多少条记录
     */
    private Long total;

    /**
     * 总页数
     */
    private Long pages;

    /**
     * 数据
     */
    private List data;
}
