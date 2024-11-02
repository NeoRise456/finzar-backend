package pe.edu.upc.smartfinance.finzar.wallets.interfaces.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.smartfinance.finzar.wallets.domain.model.queries.GetAllWalletsQuery;
import pe.edu.upc.smartfinance.finzar.wallets.domain.model.queries.GetWalletByIdQuery;
import pe.edu.upc.smartfinance.finzar.wallets.domain.services.WalletQueryService;
import pe.edu.upc.smartfinance.finzar.wallets.domain.services.WalletCommandService;
import pe.edu.upc.smartfinance.finzar.wallets.interfaces.rest.resources.CreateWalletResource;
import pe.edu.upc.smartfinance.finzar.wallets.interfaces.rest.resources.WalletResource;
import pe.edu.upc.smartfinance.finzar.wallets.interfaces.rest.transform.CreateWalletCommandFromResourceAssembler;
import pe.edu.upc.smartfinance.finzar.wallets.interfaces.rest.transform.WalletResourceFromEntityAssembler;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", methods = { RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE })
@RestController
@RequestMapping(value = "/api/v1/wallets", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Wallets", description = "Wallets Management Endpoints")
public class WalletController {

    private final WalletQueryService walletQueryService;
    private final WalletCommandService walletCommandService;

    public WalletController(WalletQueryService walletQueryService, WalletCommandService walletCommandService) {
        this.walletQueryService = walletQueryService;
        this.walletCommandService = walletCommandService;
    }

    @PostMapping
    public ResponseEntity<WalletResource> createWallet(@RequestBody CreateWalletResource resource){
        var createWalletCommand = CreateWalletCommandFromResourceAssembler.toCommandFromResource(resource);
        var walletId = walletCommandService.handle(createWalletCommand);

        if (walletId.equals(0L)){
            return ResponseEntity.badRequest().build();
        }

        var getWalletByIdQuery = new GetWalletByIdQuery(walletId);
        var optionalWallet = this.walletQueryService.handle(getWalletByIdQuery);

        var walletResource = WalletResourceFromEntityAssembler.toResourceFromEntity(optionalWallet.get());
        return new ResponseEntity<>(walletResource, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<WalletResource>> getAllWallets() {
        var getAllWalletsQuery = new GetAllWalletsQuery();
        var wallets = this.walletQueryService.handle(getAllWalletsQuery);
        var walletResources = wallets.stream()
                .map(WalletResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());

        return ResponseEntity.ok(walletResources);
    }

    @GetMapping("/{walletId}")
    public ResponseEntity<WalletResource> getWalletById(@PathVariable Long walletId) {

        var getWalletByIdQuery = new GetWalletByIdQuery(walletId);
        var optionalWallet = this.walletQueryService.handle(getWalletByIdQuery);

        if (optionalWallet.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        var walletResource = WalletResourceFromEntityAssembler.toResourceFromEntity(optionalWallet.get());

        return ResponseEntity.ok(walletResource);
    }

    //TODO: getWalletByUserId

    //TODO: updateWallet






}
