public class CreditCard extends PaymentMethod implements Refundable {

    private String cardNumber;
    private double creditLimit;

    public CreditCard(String ownerName, double balance, String cardNumber, double creditLimit) {
        super("Credit Card", ownerName, balance);
        this.cardNumber = cardNumber;
        this.creditLimit = creditLimit;
    }

    @Override
    public boolean validatePayment(double amount) {
        return amount > 0 && amount <= creditLimit && amount <= (balance + creditLimit);
    }

    @Override
    public boolean processPayment(double amount) {
        if (!validatePayment(amount)) {
            System.out.println("  ✗ Credit Card payment FAILED: Validation error for $" +
                String.format("%.2f", amount));
            return false;
        }
        balance -= amount;
        System.out.println("  ✓ Credit Card payment of $" + String.format("%.2f", amount) +
            " processed successfully.");
        System.out.println("    Card: ****" + cardNumber.substring(cardNumber.length() - 4));
        return true;
    }

    @Override
    public String getPaymentDetails() {
        return String.format("Credit Card [****%s] | Limit: $%.2f | Balance: $%.2f",
            cardNumber.substring(cardNumber.length() - 4), creditLimit, balance);
    }

    @Override
    public boolean refund(double amount) {
        if (amount <= 0) {
            System.out.println("  ✗ Refund FAILED: Invalid amount.");
            return false;
        }
        balance += amount;
        System.out.println("  ✓ Refund of $" + String.format("%.2f", amount) +
            " to Credit Card successful.");
        return true;
    }

    public String getCardNumber() { return cardNumber; }
    public double getCreditLimit() { return creditLimit; }
}
