package com.example.springdemo.service;

import com.example.springdemo.pojo.User;

/**
 * @author:zhaoxuliang.
 * @date: 2019/4/16.
 * @time: 11:17.
 */
public interface UserService {
    User getUser(Long id);
    int insertUser(User user);

}
