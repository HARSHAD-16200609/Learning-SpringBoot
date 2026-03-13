package com.example.Journal.controller;

import com.example.Journal.apiResponse.weatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WeatherService {

    @Value("${weatherapikey}")
    private  String apikey;

    @Autowired
    private RestTemplate restTemplate;

    private String API = "https://api.openweathermap.org/data/2.5/weather?q=CITY_NAME&appid=YOUR_API_KEY";

    weatherResponse getWeather(String city) {
        String FinalApi = API.replace("CITY_NAME", city).replace("YOUR_API_KEY", apikey);
       ResponseEntity<weatherResponse>  res= restTemplate.exchange(FinalApi, HttpMethod.GET, null,weatherResponse.class);
weatherResponse wresponse = res.getBody();
     return wresponse;
    }
}
