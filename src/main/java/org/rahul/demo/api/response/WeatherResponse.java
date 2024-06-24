package org.rahul.demo.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeatherResponse{
    private Current current;

    @Getter
    @Setter
    public static class Current{
        @JsonProperty("temp_c")
        private double tempInC;

        @JsonProperty("feelslike_c")
        private double feelslikeC;

        private Condition condition;
    }

    @Getter
    @Setter
    public static class Condition{
        private String text;
    }
}
