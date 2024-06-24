package org.rahul.demo.service;

import org.rahul.demo.api.response.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.RestClientException;

@Component
public class WeatherService {
    private static final String apiKey = "ad30d5cdebdd4f438a4211342242306";

    private static final String API = "http://api.weatherapi.com/v1/current.json?key=API_KEY&q=CITY&aqi=no";

    @Autowired
    private RestTemplate restTemplate;

    public WeatherResponse getWeather(String city){
        String finalAPI = API.replace("CITY", city).replace("API_KEY", apiKey);

        try {
            ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalAPI, HttpMethod.GET, null, WeatherResponse.class);
            return response.getBody();
        } catch (RestClientException e) {
            // Log the error (optional)
            e.printStackTrace();
            return null;
        }
    }
}
