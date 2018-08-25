package com.cosme.web.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

/**
 * @author Tanlian
 * @create 2018-08-22 16:05
 **/
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChannelAddRequest {


    /**
     * 频道主图
     */
    String mainImage;

    /**
     * 频道标题
     */
    String channelTitle;

    /**
     * 日文标题
     */
    String japaneseTitle;

    /**
     * 权重
     */
    Integer weight;

    /**
     * 关联内容
     */
    String linkUrl;

    /**
     * 是否需要关联内容
     */
    boolean needLinkUrl;
}
