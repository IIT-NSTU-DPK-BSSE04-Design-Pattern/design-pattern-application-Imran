interface PaymentGateway {
    void processPayment(double amount, String currency, String cardNumber);
}

class PayPalGateway implements PaymentGateway {
    public void processPayment(double amount, String currency, String cardNumber) {
        System.out.println("Processing payment through PayPal...");

        System.out.println("Paid " + amount + " " + currency + " with PayPal using card " + cardNumber);
    }
}


class StripeGateway implements PaymentGateway {
    public void processPayment(double amount, String currency, String cardNumber) {
        System.out.println("Processing payment through Stripe...");

        System.out.println("Paid " + amount + " " + currency + " with Stripe using card " + cardNumber);
    }
}


class SquareGateway implements PaymentGateway {
    public void processPayment(double amount, String currency, String cardNumber) {
        System.out.println("Processing payment through Square...");
        // Logic for Square payment
        System.out.println("Paid " + amount + " " + currency + " with Square using card " + cardNumber);
    }
}

class PaymentProcessorFacade {
    private PaymentGateway paypalGateway;
    private PaymentGateway stripeGateway;
    private PaymentGateway squareGateway;

    public PaymentProcessorFacade() {
        this.paypalGateway = new PayPalGateway();
        this.stripeGateway = new StripeGateway();
        this.squareGateway = new SquareGateway();
    }


    public void processPayment(String provider, double amount, String currency, String cardNumber) {
        switch (provider.toLowerCase()) {
            case "paypal":
                paypalGateway.processPayment(amount, currency, cardNumber);
                break;
            case "stripe":
                stripeGateway.processPayment(amount, currency, cardNumber);
                break;
            case "square":
                squareGateway.processPayment(amount, currency, cardNumber);
                break;
            default:
                System.out.println("Invalid payment .");
                break;
        }
    }


    public static void main(String[] args) {

        PaymentProcessorFacade paymentProcessor = new PaymentProcessorFacade();


        paymentProcessor.processPayment("paypal", 10.0, "USD", "1234-5678-9876-5032");


        paymentProcessor.processPayment("stripe", 20.0, "rupee", "2345-6789-8765-4301");


        paymentProcessor.processPayment("square", 5.0, "GBP", "3456-7890-7654-3200");
    }
}