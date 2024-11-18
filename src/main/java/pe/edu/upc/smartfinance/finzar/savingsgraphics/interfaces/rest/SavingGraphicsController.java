package pe.edu.upc.smartfinance.finzar.savingsgraphics.interfaces.rest;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.smartfinance.finzar.savingsgraphics.domain.model.queries.GetSavingsGraphicsQuery;
import pe.edu.upc.smartfinance.finzar.savingsgraphics.domain.services.SavingsGraphicsQueryService;
import pe.edu.upc.smartfinance.finzar.savingsgraphics.interfaces.rest.resources.SavingGraphicResource;

import java.util.List;
import java.util.stream.Collectors;
@CrossOrigin(origins = "*", methods = { RequestMethod.GET})
@RestController
@RequestMapping(value = "/api/v1/savingsgraphics", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "SavingsGraphics", description = "Saving Graphics Management Endpoints")

public class SavingGraphicsController {
    private final SavingsGraphicsQueryService savingsGraphicsQueryService;

    public SavingGraphicsController(SavingsGraphicsQueryService savingsGraphicsQueryService) {
        this.savingsGraphicsQueryService = savingsGraphicsQueryService;
    }


}
