package safety.culture.test.services.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import safety.culture.test.api.dto.TemperatureReading;
import safety.culture.test.services.dto.Measures;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by gabriela on 13/3/18.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AverageMeasuresServiceTest {

    @InjectMocks
    AverageMeasuresServiceImpl averageMeasuresService;

    @Test
    public void testMeanNullInput() {
        Double mean = averageMeasuresService.getAverage(null);
        assertThat(mean, is(nullValue()));
    }

    @Test
    public void testMeanEmptyListInput() {
        Double mean = averageMeasuresService.getAverage(new ArrayList<>());
        assertThat(mean, is(nullValue()));
    }

    @Test
    public void testMeanListWithOneItemInput() {
        List<Double> input = new ArrayList<>();
        input.add(5d);
        Double mean = averageMeasuresService.getAverage(input);
        assertThat(mean, is(5d));
    }

    @Test
    public void testMeanListWithMultipleItemsInput() {
        List<Double> input = new ArrayList<>();
        input.add(5d);
        input.add(6d);
        input.add(7d);
        Double mean = averageMeasuresService.getAverage(input);
        assertThat(mean, is(6d));
    }

    @Test
    public void testMedianNullInput() {
        Double mean = averageMeasuresService.getMedian(null);
        assertThat(mean, is(nullValue()));
    }

    @Test
    public void testMedianEmptyListInput() {
        Double mean = averageMeasuresService.getMedian(new ArrayList<>());
        assertThat(mean, is(nullValue()));
    }

    @Test
    public void testMedianListWithOneItemInput() {
        List<Double> input = new ArrayList<>();
        input.add(5d);
        Double mean = averageMeasuresService.getMedian(input);
        assertThat(mean, is(5d));
    }

    @Test
    public void testMedianListWithOddtemsInput() {
        List<Double> input = new ArrayList<>();
        input.add(7d);
        input.add(5d);
        input.add(6d);
        Double mean = averageMeasuresService.getMedian(input);
        assertThat(mean, is(6d));
    }

    @Test
    public void testMedianListWithEvenItemsInput() {
        List<Double> input = new ArrayList<>();
        input.add(7d);
        input.add(5d);
        input.add(6d);
        input.add(10d);
        Double mean = averageMeasuresService.getMedian(input);
        assertThat(mean, is(6.5d));
    }

    @Test
    public void testModeNullInput() {
        List<Double> mode = averageMeasuresService.getMode(null);
        assertThat(mode, is(nullValue()));
    }

    @Test
    public void testModeEmptyListInput() {
        List<Double> mode = averageMeasuresService.getMode(new ArrayList<>());
        assertThat(mode.isEmpty(), is(true));
    }

    @Test
    public void testModeListWithOneItemInput() {
        List<Double> input = new ArrayList<>();
        input.add(5d);
        List<Double> mode = averageMeasuresService.getMode(input);
        assertThat(mode.isEmpty(), is(true));
    }

    @Test
    public void testModeListWithUniqueItemsInput() {
        List<Double> input = new ArrayList<>();
        input.add(7d);
        input.add(5d);
        input.add(6d);
        List<Double> mode = averageMeasuresService.getMode(input);
        assertThat(mode.isEmpty(), is(true));
    }

    @Test
    public void testModeListWithSingleModeOutput() {
        List<Double> input = new ArrayList<>();
        input.add(7d);
        input.add(5d);
        input.add(5d);
        input.add(10d);
        List<Double> mode = averageMeasuresService.getMode(input);
        assertThat(mode.size(), is(1));
        assertThat(mode.get(0), is(5d));
    }

    @Test
    public void testModeListWithMultipleModeOutput() {
        List<Double> input = new ArrayList<>();
        input.add(7d);
        input.add(5d);
        input.add(5d);
        input.add(10d);
        input.add(10d);
        input.add(8d);
        List<Double> mode = averageMeasuresService.getMode(input);
        assertThat(mode.size(), equalTo(2));
        assertThat(mode.contains(5d), is(true));
        assertThat(mode.contains(10d), is(true));
    }

    @Test
    public void testGetAverageMeasuresNullInput() {
        List<Measures> measures = averageMeasuresService.getAverageMeasures(null);
        assertThat(measures, is(nullValue()));
    }

    @Test
    public void testGetAverageMeasuresEmptyInput() {
        List<Measures> measures = averageMeasuresService.getAverageMeasures(new ArrayList<>());
        assertThat(measures.isEmpty(), is(true));
    }

    @Test
    public void testGetAverageMeasuresValidInput() {
        List<TemperatureReading> readings = new ArrayList<>();
        readings.add(temperatureReadingBuilder("a", 4d));
        readings.add(temperatureReadingBuilder("b", 10d));
        readings.add(temperatureReadingBuilder("a", 7d));
        readings.add(temperatureReadingBuilder("a", 7d));

        List<Measures> measures = averageMeasuresService.getAverageMeasures(readings);
        assertThat(measures.isEmpty(), is(false));
        assertThat(measures.size(), is(2));

        for (Measures m : measures) {
            if (m.getId().equals("a")) {
                assertThat(m.getAverage(), is(6d));
                assertThat(m.getMedian(), is(7d));
                assertThat(m.getMode().get(0), is(7d));
            } else {
                assertThat(m.getAverage(), is(10d));
                assertThat(m.getMedian(), is(10d));
                assertThat(m.getMode().isEmpty(), is(true));
            }
        }

    }

    private TemperatureReading temperatureReadingBuilder(String id, Double temp) {
        TemperatureReading reading = new TemperatureReading();
        reading.setId(id);
        reading.setTemperature(temp);
        reading.setTimestamp(null);

        return reading;
    }

}
