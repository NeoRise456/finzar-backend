package pe.edu.upc.smartfinance.finzar.wallets.domain.model.commands;

public record AddToBalanceByWalletIdCommand( Long walletId, Double amount) {
}
