package seedu.address.model.delivery.exceptions;

/**
 * Signals that the operation will result in duplicate Deliveries (Deliveries are considered duplicates if their
 * start and end dates overlap).
 */
public class DuplicateDeliveryException extends RuntimeException {
    public DuplicateDeliveryException() {
        super("Operation would result in duplicate deliveries");
    }
}
