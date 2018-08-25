package com.cosme.web.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 表记录通用状态码
 *
 * @Author: 黄志泉
 * @Datetime: 2015-09-18 9:02
 */
@AllArgsConstructor
@Getter
public enum StatusCode {

    DELETE(-1, "删除"),
    DISABLED(0, "禁用"),
    ENABLED(1, "启用");

    private int id;
    private String name;

    public static StatusCode getById(int id) {
        for (StatusCode statusCode : values()) {
            if (statusCode.getId() == id) {
                return statusCode;
            }
        }
        String errorMsg = String.format("id(%d) is not match", id);
        throw new IllegalArgumentException(errorMsg);
    }

    public static StatusCode getByName(String name) {
        for (StatusCode statusCode : values()) {
            if (statusCode.getName().equalsIgnoreCase(name)) {
                return statusCode;
            }
        }
        String errorMsg = String.format("name(%s) is not match", name);
        throw new IllegalArgumentException(errorMsg);
    }
}
