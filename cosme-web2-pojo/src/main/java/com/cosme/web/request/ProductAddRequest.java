package com.cosme.web.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Date;

/**
 * @author Tanlian
 * @create 2018-08-22 16:06
 **/
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductAddRequest {


    /**
     * 商品主图
     */
    String mainImage;

    /**
     * 商品标题
     */
    String productTitle;

    /**
     * 日文标题
     */
    String japaneseTitle;

    /**
     * 发布时间
     */
    Date releaseTime;

    /**
     * 产品功效
     */
    String effect;

    /**
     * 商品频道id
     */
    Integer channelId;

    /**
     * 权重
     */
    Integer weight;

    /**
     * 条形码
     */
    String barCode;
}
