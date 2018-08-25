package com.cosme.common;

import org.springframework.beans.BeanUtils;

/**
 * @Author: 黄志泉
 * @Datetime: 2016-07-07 16:44
 */
public abstract class BaseTransformer {

    public static <S, T> T convert(S input, T output) {
        BeanUtils.copyProperties(input, output);
        return output;
    }
}
