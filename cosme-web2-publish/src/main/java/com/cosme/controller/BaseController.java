package com.cosme.controller;

import com.cosme.service.UserService;
import com.cosme.util.SessionUtils;
import com.cosme.web.dto.UserDTO;
import com.cosme.web.queryParam.UserQueryParam;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Tanlian
 * @create 2018-08-26 13:05
 **/
public class BaseController {

    @Autowired
    private UserService userService;

    /**
     * 获取当前用户登录的ID
     *
     * @param response
     * @param request
     * @return
     */
    public String getOnlineUserId(HttpServletRequest request, HttpServletResponse response ) {
        return SessionUtils.getUserId(response, request);
    }

    /**
     * 获取当前用户的信息
     *
     * @param request
     * @param response
     */
    public UserDTO getUserDetails(HttpServletRequest request, HttpServletResponse response) {
        String userId = SessionUtils.getUserId(response, request);
        if (userId == null) {
            return null;
        }
        UserQueryParam queryParam = new UserQueryParam();
        queryParam.setUserId(userId);
        UserDTO userDTO = userService.queryUserByParam(queryParam);
        return userDTO;
    }
}
