package safety.culture.test.api.dto;

import java.util.List;

/**
 * Created by gabriela on 13/3/18.
 *
 * Request object containing multiple temperature readings by multiple sensors.
 *
 */
public class TemperatureReadingRequest {

    private List<TemperatureReading> temperatureReadings;

    public List<TemperatureReading> getTemperatureReadings() {
        return temperatureReadings;
    }

    public void setTemperatureReadings(List<TemperatureReading> temperatureReadings) {
        this.temperatureReadings = temperatureReadings;
    }

}
