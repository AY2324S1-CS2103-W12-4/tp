package seedu.address.testutil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.timetable.DatedEvent;
import seedu.address.model.person.timetable.FreeTime;
import seedu.address.model.tag.Tag;
import seedu.address.model.user.User;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building User objects.
 */
public class UserBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";

    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private Set<FreeTime> freeTimes;
    private Set<Tag> tags;
    private ArrayList<DatedEvent> datedEvents;

    /**
     * Sets the {@code datedEvents} of the {@code User} that we are building.
     */
    public UserBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        freeTimes = new HashSet<>();
        tags = new HashSet<>();
        datedEvents = new ArrayList<>();
    }

    /**
     * Initializes the UserBuilder with the data of {@code userToCopy}.
     */
    public UserBuilder(User userToCopy) {
        name = userToCopy.getName();
        phone = userToCopy.getPhone();
        email = userToCopy.getEmail();
        address = userToCopy.getAddress();
        freeTimes = new HashSet<>(userToCopy.getFreeTimes());
        tags = new HashSet<>(userToCopy.getTags());
        datedEvents = new ArrayList<>(userToCopy.getDatedEvents());
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public UserBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Person} that we are building.
     */
    public UserBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Parses the {@code freeTimes} into a {@code Set<FreeTime>} and set it to the {@code Person} that we are building.
     */
    public UserBuilder withFreeTimes(String ... freeTimes) {
        this.freeTimes = SampleDataUtil.getFreeTimeSet(freeTimes);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Person} that we are building.
     */
    public UserBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Person} that we are building.
     */
    public UserBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Person} that we are building.
     */
    public UserBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code datedEvents} of the {@code User} that we are building.
     */
    public UserBuilder withDatedEvents(ArrayList<DatedEvent> datedEvents) {
        this.datedEvents = datedEvents;
        return this;
    }

    public User build() {
        return new User(name, phone, email, address, freeTimes, tags, datedEvents);
    }

}
