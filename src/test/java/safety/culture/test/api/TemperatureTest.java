package safety.culture.test.api;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import safety.culture.test.api.dto.TemperatureReading;
import safety.culture.test.services.AverageMeasuresService;
import safety.culture.test.services.dto.Measures;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

/**
 * Created by gabriela on 13/3/18.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TemperatureTest {

    @Mock
    AverageMeasuresService averageMeasuresService;

    @InjectMocks
    Temperature temperature;

    @Test
    public void testTemperatureControllerInvalidInput() {
        ResponseEntity responseEntity = temperature.getAverageMeasures(null);
        assertThat(responseEntity.getStatusCode(), is(HttpStatus.BAD_REQUEST));
        assertThat(responseEntity.getStatusCodeValue(), is(HttpStatus.BAD_REQUEST.value()));
        assertThat(responseEntity.getBody(), is(temperature.getBadRequestBody()));
    }

    @Test
    public void testTemperatureControllerInvalidOutputFromService() {
        List<TemperatureReading> readingList = new ArrayList<>();
        when(averageMeasuresService.getAverageMeasures(readingList)).thenReturn(null);
        ResponseEntity responseEntity = temperature.getAverageMeasures(readingList);

        assertThat(responseEntity.getStatusCode(), is(HttpStatus.BAD_REQUEST));
        assertThat(responseEntity.getStatusCodeValue(), is(HttpStatus.BAD_REQUEST.value()));
        assertThat(responseEntity.getBody(), is(temperature.getBadRequestBody()));
    }

    @Test
    public void testTemperatureControllerValidOutputFromService() {
        List<TemperatureReading> readingList = new ArrayList<>();
        List<Measures> measuresList = new ArrayList<>();
        Measures measures = new Measures();
        measures.setAverage(0d);
        measures.setMedian(0d);
        measures.setMode(new ArrayList<>());
        measures.setId("id");
        measuresList.add(measures);

        when(averageMeasuresService.getAverageMeasures(readingList)).thenReturn(measuresList);
        ResponseEntity responseEntity = temperature.getAverageMeasures(readingList);

        assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));
        assertThat(responseEntity.getStatusCodeValue(), is(HttpStatus.OK.value()));
        assertThat(responseEntity.getBody(), is(measuresList));
    }


}
