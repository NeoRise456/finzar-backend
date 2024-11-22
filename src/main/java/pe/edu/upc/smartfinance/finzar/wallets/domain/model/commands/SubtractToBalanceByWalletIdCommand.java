package pe.edu.upc.smartfinance.finzar.wallets.domain.model.commands;

public record SubtractToBalanceByWalletIdCommand( Long walletId, Double amount) {
}
