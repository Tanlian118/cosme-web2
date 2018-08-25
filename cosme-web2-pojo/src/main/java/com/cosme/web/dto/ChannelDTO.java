package com.cosme.web.dto;

import com.cosme.web.common.StatusCode;
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
public class ChannelDTO {
    /**
     * 频道id
     */
    Integer channelId;

    /**
     * 操作员id
     */
    String operaterId;

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

    /**
     * 商品信息
     */
    List<ProductDTO> productDTOs;
}
