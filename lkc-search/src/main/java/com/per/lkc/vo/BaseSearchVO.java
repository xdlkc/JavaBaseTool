package com.per.lkc.vo;

import lombok.Data;

/**
 * 描述用途
 * <p>
 * </p>
 * DATE 2019/12/9.
 *
 * @author zhangjunbo.
 */
@Data
public class BaseSearchVO {
    /**
     * 搜索词
     */
    private String q;
    /**
     * 页码
     */
    private Integer pageNo;

    /**
     * 偏移量
     */
    private Integer offset;

    /**
     * 分页大小
     */
    private Integer pageSize;

    private Integer type;

    private Integer timeType;

    private Integer sourceType;

    private Boolean searchNoAuth;

    private String parentSearchId;

    public BaseSearchVO() {
    }
}
