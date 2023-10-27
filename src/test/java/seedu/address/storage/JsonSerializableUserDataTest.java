package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.user.ReadOnlyUserData;
import seedu.address.model.user.UserData;
import seedu.address.testutil.UserBuilder;

public class JsonSerializableUserDataTest {
    @Test
    public void constructor_validJsonAdaptedPerson_success() throws IllegalValueException {
        JsonAdaptedUser jsonAdaptedUser = new JsonAdaptedUser(new UserBuilder().build());
        JsonSerializableUserData userData = new JsonSerializableUserData(jsonAdaptedUser);
        assertEquals(new UserBuilder().build(), userData.toModelType().getUser());
    }

    @Test
    public void constructor_validReadOnlyUserData_success() throws IllegalValueException {
        ReadOnlyUserData readOnlyUserData = new UserData(new UserBuilder().build());
        JsonSerializableUserData userData = new JsonSerializableUserData(readOnlyUserData);
        assertEquals(new UserBuilder().build(), userData.toModelType().getUser());
    }

    @Test
    public void toModelType_validJsonSerializableUserData_success() throws IllegalValueException {
        JsonAdaptedUser jsonAdaptedUser = new JsonAdaptedUser(new UserBuilder().build());
        JsonSerializableUserData userData = new JsonSerializableUserData(jsonAdaptedUser);
        UserData modelUserData = userData.toModelType();
        assertEquals(new UserBuilder().build(), modelUserData.getUser());
    }

}
