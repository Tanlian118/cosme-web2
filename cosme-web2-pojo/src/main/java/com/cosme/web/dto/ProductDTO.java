package com.cosme.web.dto;

import com.cosme.web.common.StatusCode;
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
public class ProductDTO {

    /**
     * 商品id
     */
    Integer productId;

    /**
     * 操作员Id
     */
    Integer operaterId;

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
     * 权重
     */
    Integer weight;

    /**
     * 频道id
     */
    Integer channelId;

    /**
     * 频道标题
     */
    String channelTitle;

    /**
     * 条形码
     */
    String barCode;

    /**
     * 状态
     */
    StatusCode status;

    /**
     * 创建时间
     */
    Date createTime;

    /**
     * 更新时间
     */
    Date updateTime;
}
