package seedu.address.model.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.UserBuilder;

public class UserDataTest {
    @Test
    public void setUser_newUser() {
        UserData userData = new UserData(new UserBuilder().build());
        assertEquals(userData.getUser(), new UserBuilder().build());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        UserData userData = new UserData();
        assertThrows(NullPointerException.class, () -> userData.resetData(null));
    }

    @Test
    public void resetData_filled() {
        UserData userData = new UserData(new UserBuilder().build());
        UserData newUserData = new UserData();
        userData.resetData(newUserData);
        assertEquals(userData, new UserData());
    }

    @Test
    public void equals() {
        UserData userData = new UserData(new UserBuilder().build());

        // same values -> returns true
        assertTrue(userData.equals(new UserData(new UserBuilder().build())));

        // same object -> returns true
        assertTrue(userData.equals(userData));

        // null -> returns false
        assertFalse(userData.equals(null));

        // different types -> returns false
        assertFalse(userData.equals(5.0f));

        // different values -> returns false
        assertFalse(userData.equals(new UserData()));
    }

    @Test
    public void testHashCode() {
        UserData userData = new UserData(new UserBuilder().build());
        assertEquals(userData.hashCode(), new UserData(new UserBuilder().build()).hashCode());
    }

    @Test
    public void testToString() {
        UserData userData = new UserData(new UserBuilder().build());
        String expected = "User : " + userData.getUser().toString();
        assertEquals(expected, userData.toString());
    }

}
