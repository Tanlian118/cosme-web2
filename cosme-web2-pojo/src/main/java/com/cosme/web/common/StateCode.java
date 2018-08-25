package com.cosme.web.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 返回结果状态码
 *
 * @Author: 黄志泉
 * @Datetime: 2015-07-23 11:24
 */
@AllArgsConstructor
@Getter
public enum StateCode {
    SUCCESS(0, "返回成功"),
    SYSTEM_ERROR(1, "系统错误"),
    EMPTY_RESULT(2, "结果集为空"),
    ILLEGAL_ARGS(3, "非法参数"),
    USER_OFFLINE(4, "用户未登录"),
    DB_ERROR(5, "数据库操作异常");
    /**
     * 状态码
     */
    private int value;
    /**
     * 状态码信息
     */
    private String msg;

    public Integer toJSON() {
        return value;
    }

    public StateCode fromJSON(String value) {
        return null;//没有反序列化的需求
    }
}
