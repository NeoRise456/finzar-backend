package pe.edu.upc.smartfinance.finzar.wallets.domain.model.commands;

public record CreateWalletCommand(
        Long userId,
        String name,
        Double balance
) {
}
