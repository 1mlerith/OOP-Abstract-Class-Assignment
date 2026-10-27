public class PaymentSystemDemo {

    public static void main(String[] args) {

        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║         PAYMENT SYSTEM — OOP Case Study Demo                ║");
        System.out.println("║  Topics: Abstract Class, Interface, Inheritance, Polymorphism║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");
        System.out.println();

        // 1. Creating objects using abstract class type references
        System.out.println("━━━ STEP 1: Creating Payment Methods ━━━");
        System.out.println();

        PaymentMethod cc = new CreditCard("Alice Johnson", 5000.00, "4532015112830366", 10000.00);
        PaymentMethod ew = new EWallet("Bob Smith", 1500.00, "WALLET-9981", 0.05);
        PaymentMethod bt = new BankTransfer("Charlie Lee", 25000.00, "Bank Central", "7788001234");

        System.out.println("  Created: " + cc);
        System.out.println("  Created: " + ew);
        System.out.println("  Created: " + bt);
        System.out.println();

        // 2. Polymorphism — same method call, different behavior
        System.out.println("━━━ STEP 2: Processing Payments (Polymorphism Demo) ━━━");
        System.out.println();

        PaymentMethod[] methods = { cc, ew, bt };
        double[] amounts = { 2500.00, 800.00, 5000.00 };

        for (int i = 0; i < methods.length; i++) {
            System.out.println("Processing payment with: " + methods[i].getMethodName());
            methods[i].processPayment(amounts[i]);
            System.out.println();
        }

        // 3. Using interface type reference
        System.out.println("━━━ STEP 3: Using Interface Type (Payable) ━━━");
        System.out.println();

        Payable payable = new CreditCard("Diana Prince", 3000.00, "5425233430109903", 8000.00);
        System.out.println("  Payment Details: " + payable.getPaymentDetails());
        payable.processPayment(1200.00);
        System.out.println();

        // 4. Selective interface — Refundable (instanceof + downcasting)
        System.out.println("━━━ STEP 4: Refund Demo (Selective Interface) ━━━");
        System.out.println();

        for (PaymentMethod method : methods) {
            System.out.println("Checking refund for: " + method.getMethodName());

            if (method instanceof Refundable) {
                Refundable refundable = (Refundable) method;
                refundable.refund(100.00);
            } else {
                System.out.println("  ✗ " + method.getMethodName() + " does NOT support refunds.");
            }
            System.out.println();
        }

        // 5. Failed payment — validation in action
        System.out.println("━━━ STEP 5: Failed Payment Demo (Validation) ━━━");
        System.out.println();

        System.out.println("Attempting to pay $999999 with E-Wallet (balance: $" +
            String.format("%.2f", ew.getBalance()) + "):");
        ew.processPayment(999999.00);
        System.out.println();

        // 6. Final state
        System.out.println("━━━ STEP 6: Final Payment Details ━━━");
        System.out.println();

        for (PaymentMethod method : methods) {
            System.out.println("  " + method.getPaymentDetails());
        }

        System.out.println();
        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║                    Demo Complete!                            ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");
    }
}
