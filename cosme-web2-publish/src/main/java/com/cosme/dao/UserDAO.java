package com.cosme.dao;

import com.cosme.web.entity.UserEntity;
import com.cosme.web.queryParam.UserQueryParam;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author Tanlian
 * @create 2018-08-23 21:34
 **/
public interface UserDAO {

    @Insert({"INSERT INTO cosme_user(user_id, username, password, public_key)",
             "VALUES(#{entity.userId},#{entity.username}, #{entity.password}, #{entity.publicKey})"})
    int save(@Param("entity") UserEntity userEntity);

    @Select({"SELECT user_id, username, password, public_key",
            " FROM cosme_user",
            "WHERE status = 1 AND username = #{param.username}"})
    UserEntity queryByParam(@Param("param")UserQueryParam userQueryParam);
}
