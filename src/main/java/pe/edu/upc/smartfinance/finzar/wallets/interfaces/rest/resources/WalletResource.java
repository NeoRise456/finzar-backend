package pe.edu.upc.smartfinance.finzar.wallets.interfaces.rest.resources;

public record WalletResource(
        Long id,
        String name,
        Double balance,
        SimplifiedUserResource user
) {
}
