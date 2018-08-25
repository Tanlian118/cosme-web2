package com.cosme.web.vo;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.List;

/**
 * @author Tanlian
 * @create 2018-08-22 16:05
 **/
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChannelVO {
    /**
     * 频道id
     */
    Integer channelId;

    /**
     * 频道标题
     */
    String channelTitle;

    /**
     * 日文标题
     */
    String japaneseTitle;

    /**
     * 频道主图
     */
    String mainImage;

    /**
     * 权重
     */
    Integer weight;

    /**
     * 关联内容
     */
    String linkUrl;

    /**
     * 更新时间
     */
    Date updateTime;

    /**
     * 商品信息
     */
    List<ProductVO> productVOs;

}
