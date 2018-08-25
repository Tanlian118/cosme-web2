package com.cosme.web.vo;

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
public class NewsVO {

    /**
     * 新闻id
     */
    Integer newsId;

    /**
     * 新闻图片
     */
    String newsImage;

    /**
     * 新闻标题
     */
    String newsTitle;

    /**
     * 内容简介
     */
    String contentSummary;

    /**
     * 发布时间
     */
    Date releaseTime;

    /**
     * 内容
     */
    String content;

    /**
     * 更新时间
     */
    Date updateTime;

    /**
     * 发布状态（true=已发布,false=未发布）
     */
    boolean releaseStatus;
}
