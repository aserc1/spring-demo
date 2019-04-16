package com.example.springdemo.pojo;

import lombok.Data;
import org.apache.ibatis.type.Alias;

/**
 * @author:zhaoxuliang.
 * @date: 2019/4/15.
 * @time: 18:21.
 */
@Data
@Alias(value = "user")
public class User {

    private Long id;
    private String userName;
    private String note;
    private SexEnum sex;

}
