package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.timetable.DatedEvent;

/**
 * Jackson-friendly version of {@link DatedEvent}.
 */
public class JsonAdaptedDatedEvent {

    private final String datedEvent;

    /**
     * Constructs a {@code JsonAdaptedDatedEvent} with the given {@code DatedEventName}.
     */
    @JsonCreator
    public JsonAdaptedDatedEvent(String datedEventName) {
        this.datedEvent = datedEventName;
    }

    /**
     * Converts a given {@code DatedEvent} into this class for Jackson use.
     */
    public JsonAdaptedDatedEvent(DatedEvent source) {
        datedEvent = source.toJsonString();
    }

    @JsonValue
    public String getFreeTime() {
        return datedEvent;
    }

    /**
     * Converts this Jackson-friendly adapted DatedEvent object into the model's {@code DatedEvent} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted datedEvent.
     */
    public DatedEvent toModelType() throws IllegalValueException {
        if (!DatedEvent.isValidDateTimeString(datedEvent)) {
            throw new IllegalValueException(DatedEvent.MESSAGE_CONSTRAINTS);
        }
        return new DatedEvent(datedEvent);
    }
}

