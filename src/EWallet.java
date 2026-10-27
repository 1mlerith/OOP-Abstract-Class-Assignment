public class EWallet extends PaymentMethod implements Refundable {

    private String walletId;
    private double cashbackRate;

    public EWallet(String ownerName, double balance, String walletId, double cashbackRate) {
        super("E-Wallet", ownerName, balance);
        this.walletId = walletId;
        this.cashbackRate = cashbackRate;
    }

    @Override
    public boolean validatePayment(double amount) {
        return amount > 0 && amount <= balance;
    }

    @Override
    public boolean processPayment(double amount) {
        if (!validatePayment(amount)) {
            System.out.println("  ✗ E-Wallet payment FAILED: Insufficient balance for $" +
                String.format("%.2f", amount));
            return false;
        }

        balance -= amount;

        double cashback = amount * cashbackRate;
        balance += cashback;

        System.out.println("  ✓ E-Wallet payment of $" + String.format("%.2f", amount) +
            " processed successfully.");
        System.out.println("    Wallet ID: " + walletId +
            " | Cashback earned: $" + String.format("%.2f", cashback));
        return true;
    }

    @Override
    public String getPaymentDetails() {
        return String.format("E-Wallet [%s] | Cashback Rate: %.0f%% | Balance: $%.2f",
            walletId, cashbackRate * 100, balance);
    }

    @Override
    public boolean refund(double amount) {
        if (amount <= 0) {
            System.out.println("  ✗ Refund FAILED: Invalid amount.");
            return false;
        }
        balance += amount;
        System.out.println("  ✓ Refund of $" + String.format("%.2f", amount) +
            " to E-Wallet successful.");
        return true;
    }

    public String getWalletId() { return walletId; }
    public double getCashbackRate() { return cashbackRate; }
}
