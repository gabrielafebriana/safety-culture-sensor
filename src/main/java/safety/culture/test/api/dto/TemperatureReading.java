package safety.culture.test.api.dto;

/**
 * Created by gabriela on 13/3/18.
 *
 * Temperature readings from a given sensor.
 *
 */
public class TemperatureReading {

    private String id;
    // currently we don't use the timestamp, however for future implementation,
    // timestamp might be useful for sanity checking the sensor readings
    // e.g if a sensor provide 2 different temperature readings for the same fridge id
    // with exactly the same timestamps, there might be a problem with the sensor.
    private Long timestamp;
    private Double temperature;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

}
