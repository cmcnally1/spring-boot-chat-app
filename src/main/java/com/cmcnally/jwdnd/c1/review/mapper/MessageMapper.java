package com.cmcnally.jwdnd.c1.review.mapper;

import com.cmcnally.jwdnd.c1.review.model.ChatMessage;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MessageMapper {

    @Select("SELECT * FROM MESSAGES WHERE username = #{username}")
    ChatMessage getChatMessage(String username);

    @Insert("INSERT into MESSAGES (username, messagetext) VALUES(#{username}, #{messagetext})")
    @Options(useGeneratedKeys = true, keyProperty = "messageId")
    int insert(ChatMessage message);
}
