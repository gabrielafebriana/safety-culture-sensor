package safety.culture.test.services.impl;

import safety.culture.test.api.dto.TemperatureReading;
import safety.culture.test.services.AverageMeasuresService;
import safety.culture.test.services.dto.Measures;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by gabriela on 14/3/18.
 */
@Service
public class AverageMeasuresServiceImpl implements AverageMeasuresService {

    @Override
    public List<Measures> getAverageMeasures(List<TemperatureReading> sensorReadings) {

        // returns null for invalid input (null)
        if (sensorReadings == null) {
            return null;
        }

        // create a map of id and all the temperature readings associated with the id
        Map<String, List<Double>> sensorMap = new HashMap<>();
        for (TemperatureReading reading : sensorReadings){

            if (!sensorMap.containsKey(reading.getId())) {
                sensorMap.put(reading.getId(), new ArrayList<>());
            }
            sensorMap.get(reading.getId()).add(reading.getTemperature());

        }

        // for each id, calculate the mean, median, and mode of the readings
        // the result list is unordered (based on the sample provided)
        List<Measures> measuresList = new ArrayList<>();
        for (Map.Entry<String, List<Double>> pair : sensorMap.entrySet()) {

            Double mean = getAverage(pair.getValue());
            Double median = getMedian(pair.getValue());
            List<Double> mode = getMode(pair.getValue());
            List<Double> modeRounded = null;
            // the elements inside the mode list need to be rounded to 2 places
            if (mode != null) {
                modeRounded = new ArrayList<>();
                for (Double d : mode) {
                    modeRounded.add(round(d, 2));
                }
            }

            Measures measures = new Measures();
            measures.setId(pair.getKey());
            measures.setAverage(round(mean, 2));
            measures.setMedian(round(median, 2));
            measures.setMode(modeRounded);

            measuresList.add(measures);

        }

        return measuresList;

    }

    @Override
    public Double getAverage(List<Double> readings) {
        // null is returned for null and empty list as there are
        // no average/mean for null and empty lists
        if (readings != null && !readings.isEmpty()) {
            Double sum = 0d;
            for (Double reading : readings) {
                sum += reading;
            }
            return sum/readings.size();
        }
        return null;
    }

    @Override
    public Double getMedian(List<Double> readings) {
        // null is returned for null and empty list as there are
        // no median for null and empty lists
        Double median = null;
        if (readings != null && !readings.isEmpty()) {
            Collections.sort(readings);
            int middle = readings.size() / 2;
            if (readings.size() % 2 > 0) {
                median = readings.get(middle);
            } else {
                median = (readings.get(middle) + readings.get(middle - 1))/2;
            }
        }
        return median;
    }

    @Override
    public List<Double> getMode(List<Double> readings) {
        // null is returned for invalid input (null)
        if (readings != null) {
            Map<Double, Integer> counts = new HashMap<>();
            Integer max = 0;
            Integer count;
            for (Double reading : readings) {
                if (!counts.containsKey(reading)) {
                    count = 1;
                } else {
                    count = counts.get(reading) + 1;
                }
                counts.put(reading, count);
                if (count > max) {
                    max = count;
                }
            }
            List<Double> mode = new ArrayList<>();
            // there is no mode if all items in the list are unique
            if (max > 1) {
                for (Map.Entry<Double, Integer> c : counts.entrySet()) {
                    if (Objects.equals(c.getValue(), max)) {
                        mode.add(c.getKey());
                    }
                }
            }
            return mode;
        }
        return null;
    }

    /**
     * Helper function to round a double value to specified decimal places
     * @param d
     * @param decimalPlace
     * @return the input d rounded to decimalPlace .
     *         returns null if given null input.
     */
    private static Double round(Double d, Integer decimalPlace) {
        if (d != null || decimalPlace != null) {
            BigDecimal bd = new BigDecimal(Double.toString(d));
            bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
            return bd.doubleValue();
        }

        return null;
    }

}
