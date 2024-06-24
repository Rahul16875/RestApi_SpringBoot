package org.rahul.demo.service;

import org.rahul.demo.api.response.WeatherResponse;
import org.rahul.demo.cache.AppCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
// import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.RestClientException;

// @Component
@Service
public class WeatherService {
    // private static final String apiKey = "ad30d5cdebdd4f438a4211342242306";

    @Value("${weather.api.key}")
    private String apiKey;

    // private static final String API = "http://api.weatherapi.com/v1/current.json?key=API_KEY&q=CITY&aqi=no";

    @Autowired
    private AppCache appCache;

    @Autowired
    private RestTemplate restTemplate;

    public WeatherResponse getWeather(String city){
        String finalAPI = appCache.APP_CACHE.get("weather_api").replace("<city>", city).replace("<apiKey>", apiKey);

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
