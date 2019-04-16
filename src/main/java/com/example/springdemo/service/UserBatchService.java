package com.example.springdemo.service;

import com.example.springdemo.pojo.User;

import java.util.List;

/**
 * @author:zhaoxuliang.
 * @date: 2019/4/16.
 * @time: 14:40.
 */
public interface UserBatchService {
    int insertUsers(List<User> userList);
}
