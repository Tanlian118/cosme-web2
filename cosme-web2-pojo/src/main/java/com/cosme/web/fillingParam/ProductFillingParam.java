package com.cosme.web.fillingParam;

import com.cosme.web.queryParam.ChannelQueryParam;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

/**
 * @author Tanlian
 * @create 2018-08-23 22:34
 **/

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductFillingParam {

    /**
     * 填充频道查询参数
     */
    ChannelQueryParam channelQueryParam;

    /**
     * 是否需要填充
     */
    public boolean needChannelDTO() {
        return channelQueryParam != null;
    }

}
