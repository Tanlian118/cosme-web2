package com.cosme.web.vo;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Date;

/**
 * @author Tanlian
 * @create 2018-08-22 16:03
 **/
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CarouselVO {

    /**
     * 操作员id
     */
    String operaterId;

    /**
     * 轮播图id
     */
    Integer cosmeImageId;

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
     * 更新时间
     */
    Date updateTime;

}
