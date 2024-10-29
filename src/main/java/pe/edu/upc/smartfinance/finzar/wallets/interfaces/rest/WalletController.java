package pe.edu.upc.smartfinance.finzar.wallets.interfaces.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.smartfinance.finzar.wallets.interfaces.rest.resources.CreateWalletResource;
import pe.edu.upc.smartfinance.finzar.wallets.interfaces.rest.resources.WalletResource;

import java.util.List;

@CrossOrigin(origins = "*", methods = { RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE })
@RestController
@RequestMapping(value = "/api/v1/wallets", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Wallets", description = "Wallets Management Endpoints")
public class WalletController {
    //  private final ProfileQueryService profileQueryService;
    //  private final ProfileCommandService profileCommandService;

    public WalletController(){
        //buid services
    }

    @PostMapping
    public ResponseEntity<WalletResource> createWallet(@RequestBody CreateWalletResource resource){
        return ResponseEntity.ok().body(null);
    }

    @GetMapping
    public ResponseEntity<List<WalletResource>> getAllWallets() {
        return ResponseEntity.ok().body(null);
    }







}
