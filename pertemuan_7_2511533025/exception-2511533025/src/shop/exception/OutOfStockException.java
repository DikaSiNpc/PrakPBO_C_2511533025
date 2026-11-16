package shop.exception;

public class OutOfStockException extends ShopException {
    private static final long serialVersionUID = 1L;

    private final String sku;
    private final int requested;
    private final int available;

    public OutOfStockException(String sku, int requested, int available) {
        super(buildMessage(sku, requested, available));
        this.sku = sku;
        this.requested = requested;
        this.available = available;
    }

    /** Validates the arguments, then builds the message (works before super(...) on any Java version). */
    private static String buildMessage(String sku, int requested, int available) {
        if (requested <= available) {
            throw new IllegalArgumentException("Stock is sufficient, this is not an error");
        }
        return "Stok " + sku + " tidak cukup: diminta " + requested + ", tersedia " + available;
    }

    public String getSku() { return sku; }
    public int getRequested() { return requested; }
    public int getAvailable() { return available; }
}