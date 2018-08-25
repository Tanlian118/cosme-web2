package com.cosme.service.impl;

import com.cosme.dao.UserDAO;
import com.cosme.service.UserService;
import com.cosme.transformers.UserTransformer;
import com.cosme.web.common.ResultDTO;
import com.cosme.web.dto.UserDTO;
import com.cosme.web.entity.UserEntity;
import com.cosme.web.queryParam.UserQueryParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Tanlian
 * @create 2018-08-23 21:33
 **/
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public ResultDTO<Void> addUser(UserDTO userDTO) {
        UserEntity userEntity = UserTransformer.DTO_TO_ENTITY.apply(userDTO);
        userDAO.save(userEntity);
        return ResultDTO.successfy();
    }

    @Override
    public UserDTO queryUserByParam(UserQueryParam userQueryParam) {
        UserEntity userEntity = userDAO.queryByParam(userQueryParam);
        UserDTO userDTO = UserTransformer.ENTITY_TO_DTO.apply(userEntity);
        return userDTO;
    }
}
