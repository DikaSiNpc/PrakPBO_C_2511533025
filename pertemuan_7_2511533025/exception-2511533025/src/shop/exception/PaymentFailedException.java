package shop.exception;

/** The payment could not be completed (bank outage, insufficient balance, ...). */
public class PaymentFailedException extends ShopException {
    private static final long serialVersionUID = 1L;

    public PaymentFailedException(String message) {
        super(message);
    }

    public PaymentFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}