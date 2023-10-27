package seedu.address.model.user;

import java.util.ArrayList;

import seedu.address.model.person.timetable.DatedEvent;

/**
 * Unmodifiable view of user data.
 */
public interface ReadOnlyUserData {

    /**
     * Returns the user.
     * @return
     */
    User getUser();

    /**
     * Returns the dated events.
     * @return
     */
    ArrayList<DatedEvent> getDatedEvents();
}
