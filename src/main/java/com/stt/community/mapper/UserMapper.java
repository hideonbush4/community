package com.stt.community.mapper;

import com.stt.community.model.User;
import org.apache.ibatis.annotations.*;

/**
 * @create 2020-10-13 11:27
 */
@Mapper
public interface UserMapper {
    @Insert("insert into springboot.user(name,account_id,token,gmt_create,gmt_modified,avatar_url) " +
            "values (#{name},#{account_id},#{token},#{gmt_create},#{gmt_modified},#{avatar_url})")
     void insert(User user);

    @Select("select * from springboot.user where token=#{token}")
    User findByToken(@Param("token") String token);

    @Select("select * from springboot.user where id=#{id}")
    User findById(@Param("id") Integer id);

    @Select("select * from springboot.user where account_id=#{account_id}")
    User findByAccountId(@Param("account_id") String account_id);

    @Update("update user set name=#{name},token=#{token},gmt_modified=#{gmt_modified},avatar_url=#{avatar_url}" +
            "where id=#{id}")
    void update(User dbUser);
}
