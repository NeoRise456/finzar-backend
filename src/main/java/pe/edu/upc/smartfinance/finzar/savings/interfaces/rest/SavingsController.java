package pe.edu.upc.smartfinance.finzar.savings.interfaces.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.smartfinance.finzar.savings.domain.model.aggregates.Saving;
import pe.edu.upc.smartfinance.finzar.savings.domain.services.SavingQueryService;
import pe.edu.upc.smartfinance.finzar.savings.infrastructure.persistence.jpa.repositories.SavingRepository;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", methods = { RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE })
@RestController
@RequestMapping(value = "/api/v1/savings", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Savings", description = "Saving Management Endpoints")

public class SavingsController {

    private final SavingQueryService savingQueryService;

    public SavingsController(SavingQueryService savingQueryService) {
        this.savingQueryService = savingQueryService;
    }



    @GetMapping
    public List<Saving> getAllSavings() {
        return savingQueryService.getAllSavings();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Saving> getSavingById(@PathVariable Long id) {
        return savingQueryService.getSavingById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Saving createSaving(@RequestBody Saving saving) {
        return savingQueryService.createSaving(saving);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Saving> updateSaving(@PathVariable Long id, @RequestBody Saving saving) {
        return savingQueryService.getSavingById(id)
                .map(existingSaving -> ResponseEntity.ok(savingQueryService.updateSaving(id, saving)))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteSaving(@PathVariable Long id) {
        return savingQueryService.getSavingById(id)
                .map(existingSaving -> {
                    savingQueryService.deleteSaving(id);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/existsByName/{name}")
    public ResponseEntity<Boolean> existsByName(@PathVariable String name) {
        boolean exists = savingQueryService.existsByName(name);
        return ResponseEntity.ok(exists);
    }

    @GetMapping("/existsByNameAndIdIsNot/{name}/{id}")
    public ResponseEntity<Boolean> existsByNameAndIdIsNot(@PathVariable String name, @PathVariable Long id) {
        boolean exists = savingQueryService.existsByNameAndIdIsNot(name, id);
        return ResponseEntity.ok(exists);
    }

    @GetMapping("/findByName/{name}")
    public ResponseEntity<java.util.Optional<Saving>> findByName(@PathVariable String name) {
        Optional<Saving> saving = savingQueryService.findByName(name);
        return ResponseEntity.ok(saving);
    }

}
