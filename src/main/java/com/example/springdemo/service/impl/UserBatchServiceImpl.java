package com.example.springdemo.service.impl;

import com.example.springdemo.pojo.User;
import com.example.springdemo.service.UserBatchService;
import com.example.springdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author:zhaoxuliang.
 * @date: 2019/4/16.
 * @time: 15:27.
 */
@Service
public class UserBatchServiceImpl implements UserBatchService {

    @Autowired
    private UserService userService;

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRES_NEW)
    public int insertUsers(List<User> userList) {
        int count=0;
        for(User user:userList){
            count+=userService.insertUser(user);
        }
        return count;
    }
}
