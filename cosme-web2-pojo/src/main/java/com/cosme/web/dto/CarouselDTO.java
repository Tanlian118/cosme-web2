package com.cosme.web.dto;

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
public class CarouselDTO {


    /**
     * 操作员id
     */
    String operaterId;

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

    /**
     * 操作员姓名
     */
    String username;

}
