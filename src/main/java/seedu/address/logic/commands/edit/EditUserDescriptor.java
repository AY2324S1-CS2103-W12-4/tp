package seedu.address.logic.commands.edit;

import java.util.ArrayList;
import java.util.Optional;

import seedu.address.model.person.timetable.DatedEvent;

/**
 * Stores the details to edit the user with. Each non-empty field value will replace the
 * corresponding field value of the user.
 */
public class EditUserDescriptor extends EditPersonDescriptor {
    private ArrayList<DatedEvent> datedEvents = new ArrayList<>();

    public EditUserDescriptor() {
        super();
    }

    /**
     * Copy constructor.
     * A defensive copy of {@code tags} is used internally.
     */
    public EditUserDescriptor(EditUserDescriptor toCopy) {
        super(toCopy);
        setDatedEvents(toCopy.datedEvents);
    }

    public Optional<ArrayList<DatedEvent>> getDatedEvents() {
        return Optional.ofNullable(datedEvents);
    }

    public void setDatedEvents(ArrayList<DatedEvent> datedEvents) {
        this.datedEvents = (datedEvents != null) ? new ArrayList<>(datedEvents) : null;
    }
}
