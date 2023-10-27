package seedu.address.logic.commands;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javafx.collections.ObservableList;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.timetable.TimeBlock;

/**
 * Represents a command that finds all contacts with the same free time as the User.
 * A CommonFreetimeCommand object can be created with or without a specified contact name.
 * If no name is specified, the command returns the user's common free time with all contacts.
 * If a name is specified, the command returns the user's common free time with the specified contact.
 * Inherits from the Command class and overrides its execute method.
 */

public class CommonFreetimeCommand extends Command {

    public static final String COMMAND_WORD = "cft";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Finds all contacts with the same free time as the User.\n"
            + "Example: " + COMMAND_WORD;
    public static final String MESSAGE_COMMON_FREETIME_SUCCESS =
            "Here are the contacts with the same free time as you: \n";
    public static final String MESSAGE_NO_FREE_TIME = "You have no free time!";
    public static final String MESSAGE_NO_CONTACTS = "You have no contacts with the same free time as you!";

    private Name name = null; // name of person (user) to find common free time with

    public CommonFreetimeCommand() {
    }

    public CommonFreetimeCommand(Name name) {
        this.name = name;
    }

    /**
     * Executes the CommonFreetimeCommand to find common free time between user and person.
     * If no name is specified, the command returns the user's common free time with all contacts.
     * If a name is specified, the command returns the user's common free time with the specified contact.
     *
     * @param model {@code Model} which the command should operate on.
     * @return a CommandResult object that contains the result of executing the command.
     * @throws CommandException if an error occurs during command execution.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        ArrayList<Person> overlappingContacts = new ArrayList<>();

        // If user has no free time, return error message
        if (model.getUser().getTimeblocks().isEmpty()) {
            throw new CommandException(MESSAGE_NO_FREE_TIME);
        }
        // If no name is specified, return the user's common free time with all contacts
        if (this.name == null) {
            Set<TimeBlock> userFreeTime = model.getUser().getTimeblocks();
            ObservableList<Person> contacts = model.getAddressBook().getPersonList();
            ArrayList<TimeBlock> commonFreeTime = new ArrayList<>();
            for (Person contact : contacts) {
                Set<TimeBlock> contactFreeTime = contact.getTimeblocks();
                for (TimeBlock userTime : userFreeTime) {
                    for (TimeBlock contactTime : contactFreeTime) {
                        if (userTime.isOverlap(contactTime)) {
                            commonFreeTime.add(userTime.overlap(contactTime));
                            overlappingContacts.add(contact);
                        }
                    }
                }
            }
            if (commonFreeTime.isEmpty()) {
                throw new CommandException(MESSAGE_NO_CONTACTS);
            } else {
                StringBuilder sb = new StringBuilder(MESSAGE_COMMON_FREETIME_SUCCESS);
                int i = 0;
                for (Person contact : overlappingContacts) {
                    Name nameOfContact = contact.getName();
                    sb.append(nameOfContact).append(" is free at ");
                    TimeBlock freeTime = commonFreeTime.get(i);
                    sb.append(freeTime.toString()).append("\n");
                    i++;
                }
                return new CommandResult(sb.toString());
            }
        } else {
            Person friend = model.getPersonWithName(this.name);
            Set<TimeBlock> userFreeTime = model.getUser().getTimeblocks();
            Set<TimeBlock> friendFreeTime = friend.getTimeblocks();
            Set<TimeBlock> commonFreeTime = new HashSet<>();
            for (TimeBlock userTime : userFreeTime) {
                for (TimeBlock friendTime : friendFreeTime) {
                    if (userTime.isOverlap(friendTime)) {
                        commonFreeTime.add(userTime.overlap(friendTime));
                    }
                }
            }
            if (commonFreeTime.isEmpty()) {
                throw new CommandException(createNoOverlapFriendMessage(friend));
            } else {
                StringBuilder sb = new StringBuilder(MESSAGE_COMMON_FREETIME_SUCCESS);
                for (TimeBlock freeTime : commonFreeTime) {
                    sb.append(friend.getName().toString()).append(" is free at ")
                            .append(freeTime.toString()).append("\n");
                }
                return new CommandResult(sb.toString());
            }
        }
    }

    public static String createNoOverlapFriendMessage(Person friend) {
        return "You and " + friend.getName().toString() + " have no common free time!";
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CommonFreetimeCommand)) {
            return false;
        }

        CommonFreetimeCommand otherCommonFreetimeCommand = (CommonFreetimeCommand) other;
        return (name == null && otherCommonFreetimeCommand.name == null)
                || name.equals(otherCommonFreetimeCommand.name);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .toString();
    }
}
