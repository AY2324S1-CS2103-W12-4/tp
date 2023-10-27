package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.user.ReadOnlyUserData;
import seedu.address.model.user.UserData;

/**
 * An Immutable UserData that is serializable to JSON format.
 */
@JsonRootName(value = "userdata")
class JsonSerializableUserData {
    private final JsonAdaptedUser user;

    /**
     * Constructs a {@code JsonSerializableUserData} with the given persons.
     */
    @JsonCreator
    public JsonSerializableUserData(@JsonProperty("user") JsonAdaptedUser user) {
        this.user = user;
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableUserData}.
     */
    public JsonSerializableUserData(ReadOnlyUserData source) {
        user = new JsonAdaptedUser(source.getUser());
    }

    /**
     * Converts this address book into the model's {@code UserData} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public UserData toModelType() throws IllegalValueException {
        UserData userData = new UserData(this.user.toModelType());
        return userData;
    }

}
