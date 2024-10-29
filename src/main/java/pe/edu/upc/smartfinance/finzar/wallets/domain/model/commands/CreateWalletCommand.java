package pe.edu.upc.smartfinance.finzar.wallets.domain.model.commands;

public record CreateWalletCommand(long userId,String name, double balance, double totalBalance) {
}
