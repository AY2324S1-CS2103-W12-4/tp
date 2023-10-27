package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.sql.Time;
import java.time.LocalDate;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.timetable.FreeTime;
import seedu.address.model.person.timetable.MeetUpEvent;
import seedu.address.model.person.timetable.TimeBlock;

public class AddEventCommand extends Command {

    public static final String COMMAND_WORD = "addevent";

    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Adds a non-recurring event to the calendar.\n"
        + "Parameters: "
        + "ev/EVENT_NAME "
        + "[n/FRIEND_NAME] "
        + "d/START_DATE "
        + "[YYYY-MM-DD] "
        + "END_DATE "
        + "[YYYY-MM-DD] "
        + "h/START_TIME "
        + "[HHMM] "
        + "END_TIME "
        + "[HHMM] \n"
        + "Example: " + COMMAND_WORD + " "
        + "n/Meeting my gay fwb "
        + "d/2020-03-01 "
        + "2020-03-01 "
        + "f/1400 "
        + "1600 ";

        private String eventName;
        private Name friendName;
        private TimeBlock time;
        private boolean reminder = true;
        private LocalDate date;

    public static final String MESSAGE_SUCCESS = "New event added: ";

    public AddEventCommand(String eventName, Name friendName, TimeBlock time,
        LocalDate date, boolean reminder) {
        
        requireAllNonNull(time);

        this.eventName = eventName;
        this.time = time;
        this.friendName = friendName;
        this.date = date;
        this.reminder = reminder;
    }

    /**
     * When successfully executed, it should add a new event to the user's timetable.
     * @param model {@code Model} which the command should operate on.
     * @return A command result in the form of a string.
     * @throws CommandException If the command is invalid.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        Person user = model.getUser();
        Person friend = model.getPersonWithName(friendName);
        MeetUpEvent event = new MeetUpEvent(eventName, time.toString(),
            date.toString(), reminder, friend);
        return new CommandResult(MESSAGE_SUCCESS + event.toString());
        
    }
}
