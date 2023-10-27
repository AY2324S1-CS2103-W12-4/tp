package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.timetable.DatedEvent;
import seedu.address.model.person.timetable.FreeTime;
import seedu.address.model.tag.Tag;
import seedu.address.model.user.User;


/**
 * Jackson-friendly version of {@link User}.
 */
class JsonAdaptedUser {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "User's %s field is missing!";

    private final String name;
    private final String phone;
    private final String email;
    private final String address;
    private final List<JsonAdaptedFreeTime> freeTimes = new ArrayList<>();
    private final List<JsonAdaptedTag> tags = new ArrayList<>();
    private final List<JsonAdaptedDatedEvent> datedEvents = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedUser} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedUser(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
            @JsonProperty("email") String email, @JsonProperty("address") String address,
            @JsonProperty("freeTimes") List<JsonAdaptedFreeTime> freeTimes,
            @JsonProperty("tags") List<JsonAdaptedTag> tags,
            @JsonProperty("datedEvents") List<JsonAdaptedDatedEvent> datedEvents) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        if (freeTimes != null) {
            this.freeTimes.addAll(freeTimes);
        }
        if (tags != null) {
            this.tags.addAll(tags);
        }

        if (datedEvents != null) {
            this.datedEvents.addAll(datedEvents);
        }
    }

    /**
     * Converts a given {@code User} into this class for Jackson use.
     */
    public JsonAdaptedUser(User source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        address = source.getAddress().value;
        freeTimes.addAll(source.getFreeTimes().stream()
                .map(JsonAdaptedFreeTime::new)
                .collect(Collectors.toList()));
        tags.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
        datedEvents.addAll(source.getDatedEvents().stream()
                .map(JsonAdaptedDatedEvent::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code User} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public User toModelType() throws IllegalValueException {
        final List<Tag> personTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tags) {
            personTags.add(tag.toModelType());
        }

        final List<FreeTime> personFreeTimes = new ArrayList<>();
        for (JsonAdaptedFreeTime freeTime : freeTimes) {
            personFreeTimes.add(freeTime.toModelType());
        }

        final List<DatedEvent> personDatedEvents = new ArrayList<>();
        for (JsonAdaptedDatedEvent datedEvent : datedEvents) {
            personDatedEvents.add(datedEvent.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Phone modelPhone = new Phone(phone);

        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelEmail = new Email(email);

        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(address)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final Address modelAddress = new Address(address);

        final Set<FreeTime> modelFreeTimes = new HashSet<>(personFreeTimes);
        final Set<Tag> modelTags = new HashSet<>(personTags);
        final ArrayList<DatedEvent> modelDatedEvents = new ArrayList<>(personDatedEvents);
        return new User(modelName, modelPhone, modelEmail, modelAddress, modelFreeTimes, modelTags, modelDatedEvents);
    }

}

