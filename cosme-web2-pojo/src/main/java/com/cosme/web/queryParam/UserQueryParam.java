package com.cosme.web.queryParam;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

/**
 * @author Tanlian
 * @create 2018-08-22 16:07
 **/
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserQueryParam {

    /**
     * 用户id
     */
    String userId;
    /**
     * 姓名
     */
    String username;

    /**
     * 密码
     */
    String password;
}
