package com.cosme.common;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

/**
 * @author Tanlian
 * @create 2018-08-25 20:17
 **/
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Appconfig {

    /**
     * 允许登录错误次数
     */
    int loginFailTimes;

    /**
     * 账户锁定时间
     */
    String waitTimes;

}
