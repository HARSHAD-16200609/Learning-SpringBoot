package com.example.Journal.apiResponse;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class weatherResponse {

    private List<Weather> weather;

    @Getter
    @Setter
    public static class Weather {
        private String description;
    }

}
