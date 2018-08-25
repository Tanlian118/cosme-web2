package com.cosme.adapter;

import com.cosme.service.UserService;
import com.cosme.util.AESUtil;
import com.cosme.util.SessionUtils;
import com.cosme.web.common.ResultDTO;
import com.cosme.web.common.StateCode;
import com.cosme.web.dto.UserDTO;
import com.cosme.web.queryParam.UserQueryParam;
import com.cosme.web.request.UserAddRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.UUID;

/**
 * @author Tanlian
 * @create 2018-08-23 21:25
 **/
@Service("userAdapter")
public class UserAdapter {

    @Autowired
    private UserService userService;

    public ResultDTO<Void> addUser(UserAddRequest userAddRequest) {
        if (userAddRequest == null) {
            return ResultDTO.fail(StateCode.ILLEGAL_ARGS, "请输入对应信息");
        }
        if (!StringUtils.hasText(userAddRequest.getUsername())) {
            return ResultDTO.fail(StateCode.ILLEGAL_ARGS, "请输入用户名");
        }
        if (!StringUtils.hasText(userAddRequest.getPassword())) {
            return ResultDTO.fail(StateCode.ILLEGAL_ARGS, "请输入密码");
        }

        UserQueryParam userQueryParam = new UserQueryParam();
        userQueryParam.setUsername(userAddRequest.getUsername());
        UserDTO exitUserDTO = userService.queryUserByParam(userQueryParam);
        if (userAddRequest.getUsername().equals(exitUserDTO.getUsername())) {
            return ResultDTO.fail(StateCode.ILLEGAL_ARGS, "该用户名已存在");
        }
        UserDTO userDTO= new UserDTO();
        userDTO.setUsername(userAddRequest.getUsername());
        userDTO.setPublicKey(SessionUtils.PUBLIC_KEY);
        UUID uuid = UUID.randomUUID();
        String userId = uuid.toString();
        userDTO.setUserId(userId.replace("-",""));
        String encryptPassword = AESUtil.encrypt(userAddRequest.getPassword(), SessionUtils.PUBLIC_KEY);
        userDTO.setPassword(encryptPassword);
        userService.addUser(userDTO);
        return ResultDTO.successfy();
    }
}
