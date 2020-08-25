package com.per.lkc.vo;

import lombok.Data;

/**
 * 描述用途
 * <p>
 * </p>
 * DATE 2019/12/23.
 *
 * @author zhangjunbo.
 */
@Data
public class SearchVO extends BaseSearchVO {
    private Integer source;
    /**
     * 检索用户
     */
    private String userName;

    public SearchVO() {
    }

}
