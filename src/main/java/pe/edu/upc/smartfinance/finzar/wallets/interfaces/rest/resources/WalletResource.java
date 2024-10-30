package pe.edu.upc.smartfinance.finzar.wallets.interfaces.rest.resources;


public record WalletResource( Long id, Long userId, String name, double balance, double totalBalance) {
}
