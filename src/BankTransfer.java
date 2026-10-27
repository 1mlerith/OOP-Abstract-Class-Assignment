public class BankTransfer extends PaymentMethod {

    private String bankName;
    private String accountNumber;

    public BankTransfer(String ownerName, double balance, String bankName, String accountNumber) {
        super("Bank Transfer", ownerName, balance);
        this.bankName = bankName;
        this.accountNumber = accountNumber;
    }

    @Override
    public boolean validatePayment(double amount) {
        return amount > 0 && amount <= balance;
    }

    @Override
    public boolean processPayment(double amount) {
        if (!validatePayment(amount)) {
            System.out.println("  ✗ Bank Transfer FAILED: Insufficient funds for $" +
                String.format("%.2f", amount));
            return false;
        }
        balance -= amount;
        System.out.println("  ✓ Bank Transfer of $" + String.format("%.2f", amount) +
            " processed successfully.");
        System.out.println("    Bank: " + bankName +
            " | Account: ****" + accountNumber.substring(accountNumber.length() - 4));
        return true;
    }

    @Override
    public String getPaymentDetails() {
        return String.format("Bank Transfer [%s - ****%s] | Balance: $%.2f",
            bankName, accountNumber.substring(accountNumber.length() - 4), balance);
    }

    public String getBankName() { return bankName; }
    public String getAccountNumber() { return accountNumber; }
}
