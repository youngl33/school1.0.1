package com.school.service.impl;

import com.school.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class RedisServiceImpl implements RedisService {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Resource(name = "stringRedisTemplate")
    ValueOperations<String, String> valOpsStr;

    @Autowired
    RedisTemplate<Object,Object> redisTemplate;

    @Resource(name = "redisTemplate")
    ValueOperations<Object, Object> valOpsObj;

    @Override
    public String getStr(String key) {
        return valOpsStr.get(key);
    }

    @Override
    public void setStr(String key, String val, long expire) {
        valOpsStr.set(key,val,expire);
    }

    @Override
    public void del(String key) {
        stringRedisTemplate.delete(key);
    }

    @Override
    public Object getObj(Object o) {
        return valOpsObj.get(o);
    }

    @Override
    public void setObj(Object o1, Object o2, long expire) {
        valOpsObj.set(o1,o2,expire);
    }

    @Override
    public void delObj(Object o) {
        redisTemplate.delete(o);
    }
}
