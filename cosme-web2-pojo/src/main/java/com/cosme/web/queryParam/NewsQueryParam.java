package com.cosme.web.queryParam;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.Set;

/**
 * @author Tanlian
 * @create 2018-08-22 16:28
 **/
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NewsQueryParam extends BaseQueryParam {

    /**
     * 新闻id
     */
    Set<Integer> newsIds;

    /**
     * 操作员id
     */
    Set<String> operaterIds;

    /**
     * 权重
     */
    Set<Integer> weights;

    /**
     * 发布时间
     */
    Date releaseTime;

    /**
     * 起始查询时间
     */
    Date startTime;
    /**
     * 终止查询时间
     */
    Date endTime;

}
