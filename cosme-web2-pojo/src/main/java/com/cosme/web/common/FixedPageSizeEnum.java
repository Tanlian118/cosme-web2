package com.cosme.web.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 页面查询使用的固定每页查询数量(防止前端穿透攻击)
 *
 * @Author: 黄志泉
 * @Datetime: 2017-06-05 19:03
 */
@AllArgsConstructor
@Getter
public enum FixedPageSizeEnum {

    TEN(10),
    TWENTY(20),
    FIFTY(50),
    ONE_HUNDRED(100);

    private final int pageSize;

    /**
     * 根据pageSize获取对应的枚举值,上限每页50个结果,下限10
     */
    public static FixedPageSizeEnum getByPageSize(Integer pageSize) {
        if (pageSize == null) {
            return TEN;
        }
        for (FixedPageSizeEnum pageSizeEnum : values()) {
            if (pageSizeEnum.getPageSize() == pageSize) {
                return pageSizeEnum;
            }
        }
        if (pageSize > ONE_HUNDRED.getPageSize()) {
            return ONE_HUNDRED;
        }
        return TEN;
    }
}
