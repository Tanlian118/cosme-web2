package com.cosme.web.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

/**
 * @author Tanlian
 * @create 2018-08-22 16:03
 **/
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CarouselAddRequest {


    /**
     * 轮播图
     */
    String carouselImage;

    /**
     * 关联内容
     */
    String linkUrl;

    /**
     * 权重
     */
    Integer weight;

    /**
     * 是否需要关联内容
     */
    boolean needLinkUrl;
}
