package com.per.lkc.vo;

import lombok.Data;

/**
 * 描述用途
 * <p>
 * </p>
 * DATE 2019/12/29.
 *
 * @author zhangjunbo.
 */
@Data
public class SearchResultVO {
    private String searchId;

    /**
     * 用户选中的文档id
     */
    private String dataId;

    /**
     * 目标url
     */
    private String url;
}
