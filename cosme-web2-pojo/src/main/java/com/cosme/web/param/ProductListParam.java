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
public class ProductListParam extends BaseQueryParam {

    /**
     * 商品id
     */
    Set<Integer> productIds;

    /**
     * 操作员id
     */
    Set<String> operaterIds;

    /**
     * 频道id
     */
    Set<Integer> channelIds;

    /**
     * 频道标题
     */
    String productTitle;

    /**
     * 权重
     */
    Set<Integer> weights;


}
