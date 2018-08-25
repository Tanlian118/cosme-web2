package com.cosme.web.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

/**
 * @author Tanlian
 * @create 2018-08-23 21:26
 **/
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserAddRequest {


    /**
     * 用户id
     */
    String userId;

    /**
     * 用户名
     */
    String username;

    /**
     * 密码
     */
    String password;

    /**
     * 密钥
     */
    String publicKey;

}
