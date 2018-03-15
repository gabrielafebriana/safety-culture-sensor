package safety.culture.test.services.dto;

import java.util.List;

/**
 * Created by gabriela on 13/3/18.
 *
 * Identified by the string id.
 * Contains the 3 kinds of average measurements (mean, median, and mode).
 *
 */

public class Measures {

    private String id;
    private Double average;
    private Double median;
    private List<Double> mode;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getAverage() {
        return average;
    }

    public void setAverage(Double average) {
        this.average = average;
    }

    public Double getMedian() {
        return median;
    }

    public void setMedian(Double median) {
        this.median = median;
    }

    public List<Double> getMode() {
        return mode;
    }

    public void setMode(List<Double> mode) {
        this.mode = mode;
    }

}
