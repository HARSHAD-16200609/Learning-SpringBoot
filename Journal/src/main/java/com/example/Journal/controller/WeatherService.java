package com.example.Journal.controller;

import com.example.Journal.apiResponse.weatherResponse;
import com.example.Journal.cache.AppCache;
import com.example.Journal.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WeatherService {

    @Value("${weatherapikey}")
    private String apikey;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AppCache appCache;

    @Autowired
    RedisService redisService;

    public weatherResponse getWeather(String city) {

        weatherResponse body = redisService.getvalRedis("weather_of "+city, weatherResponse.class);

    if (body != null) {

            return body;
        } else {

            String FinalApi = appCache.APP_CACHE.get("Weather-api").replace("<CITY_NAME>", city).replace(
                    "<YOUR_API_KEY>",
                    apikey);
            ResponseEntity<weatherResponse> res = restTemplate.exchange(FinalApi, HttpMethod.GET, null,
                    weatherResponse.class);
            body = res.getBody();
            if (body != null) {
                redisService.setvalRedis("weather_of " + city, body, 300l);
            }
            return body;
        }
    }
}
