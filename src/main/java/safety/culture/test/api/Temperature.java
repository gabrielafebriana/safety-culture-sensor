package safety.culture.test.api;

import org.springframework.web.bind.annotation.*;
import safety.culture.test.api.dto.TemperatureReading;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import safety.culture.test.services.AverageMeasuresService;
import safety.culture.test.services.dto.Measures;

import java.util.List;

/**
 * Created by gabriela on 13/3/18.
 *
 * Contains endpoints to process temperature readings by sensors.
 *
 */
@RequestMapping("/")
@RestController
public class Temperature {

    private static final String badRequestBody = "Invalid input.";

    @Autowired
    private AverageMeasuresService averageMeasuresService;

    @RequestMapping("/")
    public ResponseEntity greeting() {
        return ResponseEntity.ok("Hello!");
    }

    /**
     * Given a list of temperature readings by sensors, return a list containing
     * the 3 kinds of average measurements (mean, median, and mode) for each fridge id.
     * @param requests
     * @return a list of measurements for fridges, identified by string id.
     */
    @RequestMapping(value="/temperature/getAverages", method = RequestMethod.POST)
    public ResponseEntity getAverageMeasures(@RequestBody(required = false) List<TemperatureReading> requests) {

        if (requests == null) {
            return ResponseEntity.badRequest().body(badRequestBody);
        }

        List<Measures> measures = averageMeasuresService.getAverageMeasures(requests);
        if (measures == null) {
            return ResponseEntity.badRequest().body(badRequestBody);
        }

        return ResponseEntity.ok(measures);

    }

    /**
     * Getter for the custom bad request message for unit testing purpose.
     * @return custom bad request message.
     */
    public String getBadRequestBody() {
        return badRequestBody;
    }

}
