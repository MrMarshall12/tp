package seedu.address.model.delivery.fields;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class NumberOfDaysTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new NumberOfDays(null));
    }

    @Test
    public void constructor_invalidNumberofDays_throwsIllegalArgumentException() {
        String invalidNumberOfDays = "";
        assertThrows(IllegalArgumentException.class, () -> new NumberOfDays(invalidNumberOfDays));
    }

    @Test
    public void isValidNumberOfDays() {
        // null number of days
        assertThrows(NullPointerException.class, () -> NumberOfDays.isValidNumberOfDays(null));

        // invalid number of days
        assertFalse(NumberOfDays.isValidNumberOfDays("")); // empty string
        assertFalse(NumberOfDays.isValidNumberOfDays(" ")); // spaces only
        assertFalse(NumberOfDays.isValidNumberOfDays("a")); // one alphabet only
        assertFalse(NumberOfDays.isValidNumberOfDays("abc")); // alphabets only
        assertFalse(NumberOfDays.isValidNumberOfDays("-1")); // negative number
        assertFalse(NumberOfDays.isValidNumberOfDays("0")); // zero value

        // valid number of days
        assertTrue(NumberOfDays.isValidNumberOfDays("1")); // positive one-digit number
        assertTrue(NumberOfDays.isValidNumberOfDays("12")); // positive two-digit number
    }

    @Test
    public void equals() {
        NumberOfDays days = new NumberOfDays("12");

        // same values -> returns true
        assertTrue(days.equals(new NumberOfDays("12")));

        // same object -> returns true
        assertTrue(days.equals(days));

        // null -> returns false
        assertFalse(days.equals(null));

        // different types -> returns false
        assertFalse(days.equals(5.0f));

        // different values -> returns false
        assertFalse(days.equals(new NumberOfDays("3")));
    }
}
