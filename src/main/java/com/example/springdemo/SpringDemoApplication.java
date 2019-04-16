package com.example.springdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.transaction.PlatformTransactionManager;

import javax.annotation.PostConstruct;


@SpringBootApplication
public class SpringDemoApplication {

    @Autowired
    PlatformTransactionManager transactionManager;

    @Autowired
    private RedisTemplate redisTemplate;

    public static void main(String[] args) {
        SpringApplication.run(SpringDemoApplication.class, args);
    }

    /**
     *  显示事务管理器的类名
     */
    @PostConstruct
    public void viewTransactionManager(){
        System.out.println(transactionManager.getClass().getName());
    }


    /**
     *  设置redis的序列化器
     */
    @PostConstruct
    public void initRedisTemplate(){
        RedisSerializer stringSerializer=redisTemplate.getStringSerializer();
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);
    }

}
