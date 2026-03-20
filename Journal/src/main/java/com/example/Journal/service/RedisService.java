package com.example.Journal.service;

import com.example.Journal.apiResponse.weatherResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.lang.runtime.ObjectMethods;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class RedisService {

    @Autowired
    RedisTemplate redisTemplate;

    public <T> T getvalRedis(String Key, Class<T> entityClass) {
        try {
            Object o = redisTemplate.opsForValue().get(Key);
            ObjectMapper objectMapper = new ObjectMapper();
            log.info("Data fetched Sucessfully from Redis");
            if (o != null) {
                return objectMapper.readValue(o.toString(), entityClass);
            }
            return null;

        } catch (Exception e) {
            log.error("Error getting data in redis : ", e);
            return null;
        }

    }

    public void setvalRedis(String city, Object o, Long ttl) {
        try {

            ObjectMapper objectMapper = new ObjectMapper();
           String jsonVal =  objectMapper.writeValueAsString(o);
            redisTemplate.opsForValue().set(city, jsonVal, ttl, TimeUnit.SECONDS);
            log.info("Data Cached Sucessfully");
        } catch (Exception e) {
            log.error("Error adding data in redis : ", e);

        }

    }
}
