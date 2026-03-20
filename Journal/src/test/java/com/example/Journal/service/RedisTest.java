package com.example.Journal.service;

import com.example.Journal.apiResponse.weatherResponse;
import com.example.Journal.controller.WeatherService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RedisTest {

    @Autowired
    private RedisService redisService;

    @Autowired
    private WeatherService weatherService;

    @Test
    void testWeatherCache() {
        weatherResponse res = weatherService.getWeather("Mumbai");
        System.out.println("First call API: " + (res != null));
        weatherResponse res2 = weatherService.getWeather("Mumbai");
        System.out.println("Second call CACHE: " + (res2 != null));
    }
}
