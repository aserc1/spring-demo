package com.example.springdemo.dao;

import com.example.springdemo.pojo.User;
import org.apache.ibatis.annotations.*;

/**
 * @author:zhaoxuliang.
 * @date: 2019/4/16.
 * @time: 11:10.
 */
@Mapper
public interface UserDao {
    @Select("select id,user_name as userName,sex,note from user where id= #{id}")
    User getUserById(@Param("id") Long id);
    @Insert(value="insert into user(user_name,sex,note) values(#{userName},#{sex},#{note})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insertUser(User user);
}
