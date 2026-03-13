package com.example.Journal.controller;

import com.example.Journal.apiResponse.weatherResponse;
import com.example.Journal.cache.AppCache;
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

    public weatherResponse getWeather(String city) {
        System.out.println(appCache.APP_CACHE.get(appCache));
        String FinalApi = appCache.APP_CACHE.get("Weather-api").replace("<CITY_NAME>", city).replace("<YOUR_API_KEY>",
                apikey);
        ResponseEntity<weatherResponse> res = restTemplate.exchange(FinalApi, HttpMethod.GET, null,
                weatherResponse.class);
        weatherResponse wresponse = res.getBody();
        return wresponse;
    }
}
