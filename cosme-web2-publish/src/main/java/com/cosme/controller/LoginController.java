package com.cosme.controller;

import com.cosme.adapter.UserAdapter;
import com.cosme.common.Appconfig;
import com.cosme.service.UserService;
import com.cosme.util.AESUtil;
import com.cosme.util.SessionUtils;
import com.cosme.web.common.ResultDTO;
import com.cosme.web.common.StateCode;
import com.cosme.web.dto.UserDTO;
import com.cosme.web.queryParam.UserQueryParam;
import com.cosme.web.request.UserAddRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

/**
 * @author Tanlian
 * @create 2018-08-25 11:07
 **/
@RestController
@RequestMapping("/home")
public class LoginController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserAdapter userAdapter;
    @Autowired
    private Appconfig appconfig;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 用户登录
     *
     * @param username
     * @param password
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResultDTO<Void> login(String username, String password, HttpServletRequest request, HttpServletResponse response) {
        if (!StringUtils.hasText(username)) {
            return ResultDTO.fail(StateCode.ILLEGAL_ARGS, "请输入用户名");
        }
        if (!StringUtils.hasText(password)) {
            return ResultDTO.fail(StateCode.ILLEGAL_ARGS, "请输入密码");
        }
        UserQueryParam userQueryParam = new UserQueryParam();
        userQueryParam.setUsername(username);
        userQueryParam.setPassword(password);
        UserDTO userDTO = userService.queryUserByParam(userQueryParam);
        if (userDTO == null) {
            return ResultDTO.fail(StateCode.ILLEGAL_ARGS, "该用户不存在");
        }
        String passwordFromDB = userDTO.getPassword();
        String publicKey = userDTO.getPublicKey();
        String decrypt = AESUtil.decrypt(passwordFromDB, publicKey);
        System.out.println(decrypt);
        String encryptPassword = AESUtil.encrypt(password, publicKey);
        if (!passwordFromDB.equals(encryptPassword)) {
            Long loginFailTimes = redisTemplate.opsForValue().increment(username, 1);
            if (loginFailTimes > appconfig.getLoginFailTimes()) {
                redisTemplate.expire(username, loginFailTimes, TimeUnit.SECONDS);
                return ResultDTO.fail(StateCode.ILLEGAL_ARGS, "密码错误次数太多" + "请" + appconfig.getWaitTimes() + "秒后重试");
            }
            return ResultDTO.fail(StateCode.USER_OFFLINE, "用户密码错误,请重新输入");
        }

        String userId = userDTO.getUserId();
        SessionUtils.addCookie(request, response, userId);
        return ResultDTO.successfy();
    }

    /**
     * 注册用户信息
     *
     * @param userAddRequest
     * @return
     */
    @RequestMapping(value = "register", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResultDTO<Void> register(@RequestBody UserAddRequest userAddRequest) {
        return userAdapter.addUser(userAddRequest);
    }

    /**
     * 退出登录
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "logout", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResultDTO<Void> logout(HttpServletRequest request, HttpServletResponse response) {
        SessionUtils.deleteCookie(request, response);
        return ResultDTO.successfy();
    }
}
