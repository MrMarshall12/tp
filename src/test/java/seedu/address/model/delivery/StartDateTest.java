package seedu.address.model.delivery;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class StartDateTest {

    // EP: null string -> throws NullPointerException
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new StartDate(null));
    }

    // EP: string with invalid date -> throws IllegalArgumentException
    @Test
    public void constructor_invalidDate_throwsIllegalArgumentException() {
        String invalidDate = "";
        assertThrows(IllegalArgumentException.class, () -> new StartDate(invalidDate));
    }

    @Test
    public void isValidStartDate() {
        // EP: null start date -> throws NullPointerException
        assertThrows(NullPointerException.class, () -> StartDate.isValidStartDate(null));

        // EP: string with no date -> returns false
        // Boundary value: empty string
        assertFalse(StartDate.isValidStartDate(""));
        assertFalse(StartDate.isValidStartDate(" ")); // spaces only

        // EP: string with date of the wrong number of digits -> returns false
        assertFalse(StartDate.isValidStartDate("2020-1-01")); // month with only 1 digit

        // EP: string with date of the correct number of digits but incorrect format and invalidity -> returns false
        assertFalse(StartDate.isValidStartDate("12-01-2026")); // incorrect format

        // EP: string with valid date of the correct number of digits -> returns true
        assertTrue(StartDate.isValidStartDate("2019-10-15")); // correct format
        assertTrue(StartDate.isValidStartDate("2020-02-29")); // valid date for a leap year
    }

    @Test
    public void equals() {
        StartDate date = new StartDate("2019-10-15");

        // EP: same values -> returns true
        assertTrue(date.equals(new StartDate("2019-10-15")));

        // EP: same object -> returns true
        assertTrue(date.equals(date));

        // EP: null -> returns false
        assertFalse(date.equals(null));

        // EP: different types -> returns false
        assertFalse(date.equals(5.0f));

        // EP: different values -> returns false
        assertFalse(date.equals(new StartDate("2020-11-12")));
    }
}
