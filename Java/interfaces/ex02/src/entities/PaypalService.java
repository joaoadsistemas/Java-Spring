package entities;

public class PaypalService implements OnlinePaymentService {

    private double result;

    @Override
    public double paymentFee(double amount) {
        return amount * 0.02;
    }

    @Override
    public double interest(double amount, int month) {
        return amount * 0.01 * month;
    }

}
