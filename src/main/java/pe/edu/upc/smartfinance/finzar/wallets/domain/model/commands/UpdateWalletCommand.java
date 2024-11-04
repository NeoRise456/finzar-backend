package pe.edu.upc.smartfinance.finzar.wallets.domain.model.commands;

public record UpdateWalletCommand(
        Long walletId,
        String name,
        Double balance
) {
}
