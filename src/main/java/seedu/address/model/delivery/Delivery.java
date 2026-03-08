package seedu.address.model.delivery;

import seedu.address.commons.util.ToStringBuilder;

import java.util.Objects;

/**
 * Represents a Delivery in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Delivery {

    // Data fields
    private final StartDate startDate;
    private final EndDate endDate;
    private final DeliveryTime deliveryTime;

    /**
     * Every field must be present and not null.
     */
    public Delivery(StartDate startDate, EndDate endDate, DeliveryTime deliveryTime) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.deliveryTime = deliveryTime;
    }

    public StartDate getStartDate() {
        return startDate;
    }

    public EndDate getEndDate() {
        return endDate;
    }

    public DeliveryTime getDeliveryTime() {
        return deliveryTime;
    }

    /**
     * Returns true if both deliveries have the overlapping delivery date ranges.
     * This defines the notion of equality between two deliveries consistent
     * with the application logic.
     */
    public boolean isSameDelivery(Delivery otherDelivery) {
        if (otherDelivery == this) {
            return true;
        }

        return otherDelivery != null
                && (
                        endDate.date.isAfter(otherDelivery.startDate.date)
                    || otherDelivery.endDate.date.isAfter(startDate.date)
                );
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Delivery)) {
            return false;
        }

        Delivery otherDelivery = (Delivery) other;
        return startDate.equals(otherDelivery.startDate)
                && endDate.equals(otherDelivery.endDate)
                && deliveryTime.equals(otherDelivery.deliveryTime);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(startDate, endDate, deliveryTime);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("start date", startDate)
                .add("end date", endDate)
                .add("delivery time", deliveryTime)
                .toString();
    }
}
