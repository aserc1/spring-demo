package com.example.springdemo.controller;

import com.example.springdemo.dao.MyBatisUserDao;
import com.example.springdemo.pojo.SexEnum;
import com.example.springdemo.pojo.User;
import com.example.springdemo.service.UserBatchService;
import com.example.springdemo.service.UserService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author:zhaoxuliang.
 * @date: 2019/4/15.
 * @time: 20:21.
 */
@Data
@RestController
public class UserController {
    @Autowired
    private MyBatisUserDao myBatisUserDao;

    @Autowired
    private UserService userService;

    @Autowired
    private UserBatchService userBatchService;

    @GetMapping("/hello")
    public String hello(){
        User user=myBatisUserDao.getUser(11L);
        return "hello "+user.getUserName();
    }

    @GetMapping("/getUser")
    public User getUser(@RequestParam("id") Long id){
        return userService.getUser(id);
    }

    @GetMapping("/insertUser")
    public Map<String,Object>  insertUser(@RequestParam("name") String userName,
                                          @RequestParam("note") String note){
        User user=new User();
        user.setUserName(userName);
        user.setNote(note);
        user.setSex(SexEnum.FEMALE);
        int update=userService.insertUser(user);
        Map<String,Object> result=new HashMap<>();
        result.put("success",update==1);
        result.put("user",user);
        return result;

    }

    @GetMapping("/insertUsers")
    public Map<String,Object> insertTwoUsers(String name1,String note1,String name2,String note2){
        User user1=new User();
        user1.setUserName(name1);
        user1.setNote(note1);
        user1.setSex(SexEnum.MALE);
        User user2=new User();
        user2.setUserName(name2);
        user2.setNote(note2);
        user2.setSex(SexEnum.FEMALE);
        List<User> userList=new ArrayList<>();
        userList.add(user1);
        userList.add(user2);
        int inserts=userBatchService.insertUsers(userList);
        Map<String,Object> result=new HashMap<>();
        result.put("success",inserts>0);
        result.put("user",userList);
        return result;
    }

}
