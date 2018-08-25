package com.cosme.service;

import com.cosme.web.common.ResultDTO;
import com.cosme.web.dto.UserDTO;
import com.cosme.web.queryParam.UserQueryParam;

/**
 * @author Tanlian
 * @create 2018-08-23 21:32
 **/
public interface UserService {

    /**
     * 新增用户
     * @param userDTO
     * @return
     */
    ResultDTO<Void> addUser(UserDTO userDTO);

    /**
     * 查询用户信息
     * @param userQueryParam
     * @return
     */
    UserDTO queryUserByParam(UserQueryParam userQueryParam);
}
