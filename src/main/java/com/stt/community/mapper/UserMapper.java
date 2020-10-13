package com.stt.community.mapper;

import com.stt.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @create 2020-10-13 11:27
 */
@Mapper
public interface UserMapper {
    @Insert("insert into springboot.user(name,account_id,token,gmt_create,gmt_modified) " +
            "values (#{name},#{account_id},#{token},#{gmt_create},#{gmt_modified})")
     void insert(User user);

    @Select("select * from springboot.user where token=#{token}")
    User findByToken(@Param("token") String token);
}
