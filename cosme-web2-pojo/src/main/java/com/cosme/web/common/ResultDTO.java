package com.cosme.web.common;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 返回结果封装对象
 *
 * @Author: 黄志泉
 * @Datetime: 2015-07-23 11:12
 */
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ResultDTO<T> implements Serializable {
    /**
     * 数据，真正的结果对象
     */
    private T model;
    /**
     * 状态码
     */
    private StateCode state;
    /**
     * 信息
     */
    private String msg;

    public static <T> ResultDTO<T> successfy() {
        return new ResultDTO<>(null, StateCode.SUCCESS, StateCode.SUCCESS.getMsg());
    }

    public static <T> ResultDTO<T> successfy(T model) {
        return new ResultDTO<>(model, StateCode.SUCCESS, StateCode.SUCCESS.getMsg());
    }

    public static <T> ResultDTO<T> fail(StateCode stateCode, String errorMsg) {
        return fail(stateCode, errorMsg, null);
    }

    public static <T> ResultDTO<T> fail(StateCode stateCode, String errorMsg, T model) {
        if (errorMsg == null) {
            errorMsg = stateCode.getMsg();
        }
        return new ResultDTO<>(model, stateCode, errorMsg);
    }

    public boolean isSuccess() {
        return StateCode.SUCCESS == state;
    }
}
