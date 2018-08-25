package com.cosme.web.queryParam;

import com.cosme.web.fillingParam.ChannelFillingParam;
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
public class ChannelQueryParam extends BaseQueryParam<ChannelFillingParam>{

    /**
     * 频道id
     */
    Set<Integer> channelIds;

    /**
     * 操作员id
     */
    Set<String> operaterIds;

    /**
     * 权重
     */
    Set<Integer> weights;

}
