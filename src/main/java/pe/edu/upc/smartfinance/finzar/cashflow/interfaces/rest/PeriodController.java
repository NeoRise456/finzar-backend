package pe.edu.upc.smartfinance.finzar.cashflow.interfaces.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", methods = { RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE })
@RestController
@RequestMapping(value = "/api/v1/periods", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Periods", description = "Periods Management Endpoints")
public class PeriodController {
    //TODO: getAllPeriods

    //TODO: getPeriodById

    //TODO: createPeriod
}
