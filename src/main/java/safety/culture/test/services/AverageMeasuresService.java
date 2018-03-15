package safety.culture.test.services;

import safety.culture.test.api.dto.TemperatureReading;
import safety.culture.test.services.dto.Measures;

import java.util.List;

/**
 * Created by gabriela on 13/3/18.
 *
 * Provide services to get the 3 kinds of average measurements (mean, median, and mode).
 *
 */
public interface AverageMeasuresService {

    /**
     * Function to get all 3 kinds of average measurements (mean, median, mode)
     * @param sensorReadings
     * @return the list of average measurements
     *
     */
    List<Measures> getAverageMeasures(List<TemperatureReading> sensorReadings);

    /**
     * Function to get the mean/average of a list of Double values
     * @param readings
     * @return the mean value of readings.
     *         returns null if given invalid input such as null or empty list.
     */
    Double getAverage(List<Double> readings);

    /**
     * Function to get the median of a list of Double values
     * @param readings
     * @return the median of readings.
     *         returns null if given invalid input such as null or empty list.
     */
    Double getMedian(List<Double> readings);

    /**
     * Function to get the mode of a list of Double values
     * @param readings
     * @return the mode of readings.
     *         returns null if given invalid input such as null or empty list.
     */
    List<Double> getMode(List<Double> readings);

}
