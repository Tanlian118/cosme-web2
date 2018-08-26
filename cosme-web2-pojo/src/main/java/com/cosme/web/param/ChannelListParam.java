package com.cosme.web.param;

import com.cosme.web.queryParam.BaseQueryParam;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Set;

/**
 * @author Tanlian
 * @create 2018-08-22 16:28
 **/
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChannelListParam extends BaseQueryParam {

    /**
     * 频道id
     */
    Set<Integer> channelIds;

    /**
     * 操作员id
     */
    String operaterId;

    /**
     * 权重
     */
    Set<Integer> weights;

}
