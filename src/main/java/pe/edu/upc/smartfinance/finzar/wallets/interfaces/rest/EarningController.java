package pe.edu.upc.smartfinance.finzar.wallets.interfaces.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", methods = { RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE })
@RestController
@RequestMapping(value = "/api/v1/earnings", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Earnings", description = "Earnings Management Endpoints")
public class EarningController {
    //TODO: getEarningsByWalletId

    //TODO: getEarningsByUserId

    //TODO: getEarningsById

    //TODO: createEarning
}
