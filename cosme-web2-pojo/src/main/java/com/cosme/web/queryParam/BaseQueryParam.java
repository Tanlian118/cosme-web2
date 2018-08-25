package com.cosme.web.queryParam;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

/**
 * @author Tanlian
 * @create 2018-08-22 16:28
 **/
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BaseQueryParam<T> {

    /**
     * 分页索引
     */
    int page;

    /**
     *分页大小
     */
    Integer pageSize;

    /**
     * 是否需要分页
     */
    boolean needPagination;

    /**
     * 是否需要填充参数
     */
    T fillingParam;

}
