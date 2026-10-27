public interface Payable {
    boolean processPayment(double amount);
    String getPaymentDetails();
}
