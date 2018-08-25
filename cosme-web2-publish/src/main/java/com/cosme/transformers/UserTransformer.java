package com.cosme.transformers;

import com.cosme.common.BaseTransformer;
import com.cosme.common.SafeFunction;
import com.cosme.web.dto.UserDTO;
import com.cosme.web.entity.UserEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * @author Tanlian
 * @create 2018-08-22 21:45
 **/
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserTransformer extends BaseTransformer {

    public static final SafeFunction<UserDTO, UserEntity> DTO_TO_ENTITY = input -> convert(input, new UserEntity());

    public static final SafeFunction<UserEntity, UserDTO> ENTITY_TO_DTO = input -> convert(input, new UserDTO());

}
