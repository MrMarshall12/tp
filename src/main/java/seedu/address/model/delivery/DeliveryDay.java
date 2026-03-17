package seedu.address.model.delivery;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.DayOfWeek;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.TextStyle;
import java.util.Locale;

/**
 * Represents a Delivery's day in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidDeliveryDay(String)}
 */
public class DeliveryDay {
    public static final String MESSAGE_CONSTRAINTS =
            "day should be of the valid delivery day format";

    /**
     * Message to be shown for invalid day number format.
     * TODO: Remove or move after refactoring DeliveryDay class.
     */
    public static final String NUMBER_MESSAGE_CONSTRAINTS =
            "day number should be within the range 1-7";

    /**
     * The day must follow the format of
     * having the complete day of the week word.
     *
     * Examples of day inputs accepted by the formatter: Monday, Tuesday.
     *
     * Examples of valid input from the user
     * (after capitalization and lowercasing of some letters): monday, TUESDAY, WEDnesDay.
     */
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("EEEE", Locale.ENGLISH);

    /**
     * The day input that uses this formatter must follow the format of
     * having the number representing the day of the week.
     *
     * Examples of day number inputs accepted by the formatter: 1, 2.
     * The formatter will only successfully parse numbers in the range 1-7.
     *
     * TODO: Move the usage of this formatter.
     */
    public static final DateTimeFormatter NUMBER_FORMATTER = DateTimeFormatter.ofPattern("e");

    public final DayOfWeek day;

    /**
     * Constructs a {@code DeliveryDay}
     *
     * @param day A valid day string in the valid format.
     */
    public DeliveryDay(String day) {
        requireNonNull(day);
        checkArgument(isValidDeliveryDay(day), MESSAGE_CONSTRAINTS);
        String dayWithCorrectFormat = day.substring(0, 1).toUpperCase()
                + day.substring(1).toLowerCase();
        this.day = DayOfWeek.from(FORMATTER.parse(dayWithCorrectFormat));
    }

    /**
     * Returns true if a given string is a valid
     * day of the week in the valid format.
     */
    public static boolean isValidDeliveryDay(String test) {
        try {
            // To prevent out of bounds access.
            if (test.isEmpty()) {
                return false;
            }

            String testWithCorrectFormat = test.substring(0, 1).toUpperCase()
                    + test.substring(1).toLowerCase();
            DayOfWeek.from(FORMATTER.parse(testWithCorrectFormat));
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * Returns true if a given string is a valid
     * number representing the day of the week.
     * It should only accept numbers 1-7.
     *
     * TODO: Refactor or remove this method as part of refactoring DeliveryDay class.
     */
    public static boolean isValidDeliveryDayNumber(String test) {
        try {
            // To prevent out of bounds access.
            if (test.isEmpty()) {
                return false;
            }

            DayOfWeek.from(NUMBER_FORMATTER.parse(test));
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * Returns the word that represents
     * the day of week obtained from the day number.
     *
     * @param number The number representing the day of the week as a string.
     * @return The full word that represents the day of the week.
     *
     * Output examples: Monday, Tuesday, Thursday.
     *
     * TODO: Refactor or remove this method as part of refactoring DeliveryDay class.
     */
    public static String convertDayNumberToDayWord(String number) {
        requireNonNull(number);
        checkArgument(isValidDeliveryDayNumber(number), NUMBER_MESSAGE_CONSTRAINTS);
        DayOfWeek day = DayOfWeek.from(NUMBER_FORMATTER.parse(number));
        return day.getDisplayName(TextStyle.FULL, Locale.ENGLISH);
    }

    @Override
    public String toString() {
        return day.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeliveryDay)) {
            return false;
        }

        DeliveryDay otherDay = (DeliveryDay) other;
        return day.equals(otherDay.day);
    }

    @Override
    public int hashCode() {
        return day.hashCode();
    }
}
