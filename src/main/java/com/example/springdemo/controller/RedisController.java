package com.example.springdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisZSetCommands.Range;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.core.ZSetOperations.TypedTuple;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import java.util.*;

/**
 * @author:zhaoxuliang.
 * @date: 2019/4/16.
 * @time: 17:34.
 */
@RestController
@RequestMapping("/redis")
public class RedisController {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @GetMapping("stringAndHash")
    public Map<String,Object> testStringAndHash(){
        redisTemplate.opsForValue().set("key1","value1");
        redisTemplate.opsForValue().set("int_key","1");
        stringRedisTemplate.opsForValue().set("int","1");
        stringRedisTemplate.opsForValue().increment("int",1);

        Jedis jedis= (Jedis) stringRedisTemplate.getConnectionFactory().getConnection().getNativeConnection();
        jedis.decr("int");

        Map<String,String> hash=new HashMap<>();
        hash.put("field1","value1");
        hash.put("field2","value2");
        stringRedisTemplate.opsForHash().putAll("hash",hash);
        stringRedisTemplate.opsForHash().put("hash","field3","value3");

        BoundHashOperations hashOperations=stringRedisTemplate.boundHashOps("hash");
        hashOperations.delete("field1","field2");
        hashOperations.put("field4","field4");

        Map<String,Object> result=new HashMap<>();
        result.put("success",true);
        return result;
    }

    @GetMapping("list")
    public Map<String,Object> testList(){
        stringRedisTemplate.opsForList().leftPushAll("list1","v2","v4","v6","v8","v10");
        stringRedisTemplate.opsForList ().rightPushAll ("list2","v1","v2","v3","v4","v5","v6");

        BoundListOperations listOps = stringRedisTemplate.boundListOps("list2");
        Object result1=listOps.rightPop();
        Object result2 =listOps.index(1);
        listOps.leftPush("v0");
        Long size=listOps.size();

        List elements=listOps.range(0,size-2);
        Map<String,Object> result=new HashMap<>();
        result.put("success",true);
        result.put("elements",elements);
        result.put("result1",result1);
        result.put("result2",result2);
        return result;
    }

    @GetMapping("set")
    public Map<String,Object> testSet(){
        stringRedisTemplate.opsForSet().add("set1","v1","v1","v2","v3","v4","v5","v6");
        stringRedisTemplate.opsForSet().add("set2","v2","v4","v6","v8");

        BoundSetOperations setOperations=stringRedisTemplate.boundSetOps("set1");
        setOperations.add("v6","v7");
        setOperations.remove("v1","v7");

        Set set1=setOperations.members();
        Long size=setOperations.size();

        Set inter=setOperations.intersect("set2");
        Set diff=setOperations.diff("set2");
        Set union=setOperations.union("set2");

        Map<String,Object> result=new HashMap<>();
        result.put("success",true);
        result.put("set1",set1);
        result.put("size",size);
        result.put("inter",inter);
        result.put("diff",diff);
        result.put("union",union);
        return result;
    }

    @GetMapping("zset")
    public Map<String,Object> testZset(){
        Set<TypedTuple<String>> typedTupleSet=new HashSet<>();
        for(int i=0;i<9;i++){
            double score=i*0.1;
            TypedTuple<String> typedTuple=new DefaultTypedTuple<>("value"+i,score);
            typedTupleSet.add(typedTuple);
        }

        stringRedisTemplate.opsForZSet().add("zset1",typedTupleSet);
        BoundZSetOperations zSetOperations=stringRedisTemplate.boundZSetOps("zset1");
        zSetOperations.add("value10",0.26);
        Set<String> setRange=zSetOperations.range(1,6);
        Set<String> setScore=zSetOperations.rangeByScore(0.2,0.6);

        Range range=new Range();
        range.gt("value3");
        range.lte("value8");
        Set<String> setLex=zSetOperations.rangeByLex(range);

        zSetOperations.remove("value9","value2");

        Double score=zSetOperations.score("value8");

        Set<TypedTuple<String>> rangeSet=zSetOperations.rangeWithScores(1,6);
        Set<TypedTuple<String>> scoreSet=zSetOperations.rangeByScoreWithScores(1,6);
        Set<String> reverseSet=zSetOperations.reverseRange(2,8);

        Map<String,Object> result=new HashMap<>();
        result.put("success",true);
        result.put("setRange",setRange);
        result.put("setScore",setScore);
        result.put("rangeSet",rangeSet);
        result.put("scoreSet",scoreSet);
        result.put("reverseSet",reverseSet);
        return result;
    }
}
