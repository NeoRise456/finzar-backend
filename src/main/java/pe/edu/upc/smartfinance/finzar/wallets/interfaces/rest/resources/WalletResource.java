package pe.edu.upc.smartfinance.finzar.wallets.interfaces.rest.resources;


public record WalletResource( int id, int userId, String name, double balance, double totalBalance) {
}
