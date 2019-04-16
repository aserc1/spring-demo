package com.example.springdemo.dao;

import com.example.springdemo.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author:zhaoxuliang.
 * @date: 2019/4/15.
 * @time: 18:42.
 */
@Mapper
public interface MyBatisUserDao {

    @Select("select id , user_name as userName , sex , note from user where id= #{id}")
    User getUser(@Param("id") Long id);
}
