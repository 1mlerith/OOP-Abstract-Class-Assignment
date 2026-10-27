public abstract class PaymentMethod implements Payable {

    protected String methodName;
    protected String ownerName;
    protected double balance;

    public PaymentMethod(String methodName, String ownerName, double balance) {
        this.methodName = methodName;
        this.ownerName = ownerName;
        this.balance = balance;
    }

    public abstract boolean validatePayment(double amount);

    public String getMethodName() {
        return methodName;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public double getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return String.format("[%s] Owner: %s | Balance: $%.2f", methodName, ownerName, balance);
    }
}
