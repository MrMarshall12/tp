package seedu.address.model.delivery;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.person.Person;

/**
 * Tests whether a {@code Person} has a delivery scheduled within the given date range.
 */
public class DeliveryDatePredicate implements Predicate<Person> {

    private final LocalDate startDate;
    private final LocalDate endDate;

    /**
     * Creates a DeliveryDatePredicate that tests whether a person has a delivery on {@code date}.
     */
    public DeliveryDatePredicate(LocalDate date) {
        this(date, date);
    }

    /**
     * Creates a DeliveryDatePredicate that tests whether a person has a delivery
     * within the range from {@code startDate} to {@code endDate} (inclusive).
     */
    public DeliveryDatePredicate(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public boolean test(Person person) {

        Delivery delivery = person.getDelivery();

        if (delivery == null) {
            return false;
        }

        LocalDate deliveryStart = delivery.getStartDate().date;
        LocalDate deliveryEnd = delivery.getEndDate().date;

        LocalDate overlapStart = startDate.isAfter(deliveryStart) ? startDate : deliveryStart;
        LocalDate overlapEnd = endDate.isBefore(deliveryEnd) ? endDate : deliveryEnd;

        if (overlapStart.isAfter(overlapEnd)) {
            return false;
        }

        for (DeliveryDay deliveryDay : delivery.getDeliveryDays()) {
            LocalDate firstOccurrence = overlapStart.with(TemporalAdjusters.nextOrSame(deliveryDay.day));
            if (!firstOccurrence.isAfter(overlapEnd)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof DeliveryDatePredicate)) {
            return false;
        }

        DeliveryDatePredicate otherPredicate = (DeliveryDatePredicate) other;
        return startDate.equals(otherPredicate.startDate)
                && endDate.equals(otherPredicate.endDate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("startDate", startDate)
                .add("endDate", endDate)
                .toString();
    }
}
