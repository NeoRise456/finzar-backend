package pe.edu.upc.smartfinance.finzar.wallets.interfaces.rest.resources;

public record CreateWalletResource(int userId, String name, double balance, double totalBalance) {
}
