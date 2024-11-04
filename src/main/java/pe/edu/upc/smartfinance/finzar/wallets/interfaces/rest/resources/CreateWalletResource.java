package pe.edu.upc.smartfinance.finzar.wallets.interfaces.rest.resources;

public record CreateWalletResource(
        Long userId,
        String name,
        Double initialBalance
) {
}
