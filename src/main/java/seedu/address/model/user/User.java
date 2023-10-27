package seedu.address.model.user;

import java.util.ArrayList;
import java.util.Set;

import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.timetable.DatedEvent;
import seedu.address.model.person.timetable.FreeTime;
import seedu.address.model.tag.Tag;

/**
 * Represents a User in the address book.
 */
public class User extends Person {
    private ArrayList<DatedEvent> datedEvents = new ArrayList<>();

    /**
     * Every field must be present and not null.
     */
    public User(Name name, Phone phone, Email email, Address address,
            Set<FreeTime> freeTimes, Set<Tag> tags, ArrayList<DatedEvent> datedEvents) {
        super(name, phone, email, address, freeTimes, tags);
        this.datedEvents = datedEvents;
    };

    public User() {
        super();
    }

    /**
     * Constructor for User with a person and dated events.
     */
    public User(Person user, ArrayList<DatedEvent> datedEvents) {
        super(user.getName(), user.getPhone(), user.getEmail(), user.getAddress(), user.getFreeTimes(), user.getTags());
        this.datedEvents = datedEvents;
    }

    /**
     * Constructor for User with a person.
     */
    public User(User user) {
        super(user.getName(), user.getPhone(), user.getEmail(), user.getAddress(), user.getFreeTimes(), user.getTags());
        this.datedEvents = user.getDatedEvents();
    }

    public ArrayList<DatedEvent> getDatedEvents() {
        return datedEvents;
    }

    public void setDatedEvents(ArrayList<DatedEvent> datedEvents) {
        this.datedEvents = datedEvents;
    }

}
