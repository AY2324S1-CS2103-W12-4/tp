package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.timetable.FreeTime;
import seedu.address.model.person.timetable.TimeBlock;

/**
 * Jackson-friendly version of {@link FreeTime}.
 */
public class JsonAdaptedFreeTime {

    private final String freeTime;

    /**
     * Constructs a {@code JsonAdaptedFreeTime} with the given {@code freeTimeName}.
     */
    @JsonCreator
    public JsonAdaptedFreeTime(String freeTimeName) {
        this.freeTime = freeTimeName;
    }

    /**
     * Converts a given {@code FreeTime} into this class for Jackson use.
     */
    public JsonAdaptedFreeTime(TimeBlock source) {
        freeTime = source.timeBlockString;
    }

    @JsonValue
    public String getFreeTime() {
        return freeTime;
    }

    /**
     * Converts this Jackson-friendly adapted FreeTime object into the model's {@code FreeTime} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted freeTime.
     */
    public TimeBlock toModelType() throws IllegalValueException {
        if (!FreeTime.isValidFreeTime(freeTime)) {
            throw new IllegalValueException(FreeTime.MESSAGE_CONSTRAINTS);
        }
        return new TimeBlock(freeTime);
    }
}
