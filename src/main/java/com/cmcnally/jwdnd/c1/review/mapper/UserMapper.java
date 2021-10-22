package com.cmcnally.jwdnd.c1.review.mapper;

import com.cmcnally.jwdnd.c1.review.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    // Select a user from the database
    @Select("SELECT * FROM USERS WHERE username = #{username}")
    User getUser(String username);

    // Insert a user to the database
    @Insert("INSERT into USERS (username, salt, password, firstname, lastname) VALUES(#{username}, #{salt}, #{password}, #{firstname}, #{lastname})")
    @Options(useGeneratedKeys = true, keyProperty = "userid")
    int insert(User user);
}
