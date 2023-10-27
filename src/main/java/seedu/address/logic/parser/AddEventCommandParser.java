package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FREETIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMINDER;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.AddEventCommand;
import seedu.address.logic.commands.CommonFreetimeCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Name;
import seedu.address.model.person.timetable.FreeTime;
import seedu.address.model.person.timetable.TimeBlock;

import java.time.LocalDate;
import java.util.stream.Stream;

public class AddEventCommandParser implements Parser<AddEventCommand> {

    @Override
    public AddEventCommand parse(String userInput) throws ParseException {
        requireNonNull(userInput);

        String eventName;
        Name friendName;
        TimeBlock time;
        boolean reminder;
        LocalDate date;

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(userInput, PREFIX_EVENT, PREFIX_NAME,
                PREFIX_FREETIME, PREFIX_DATE, PREFIX_REMINDER);

        
        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_DATE, PREFIX_EVENT, PREFIX_FREETIME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        eventName = ParserUtil.parseEventName(argMultimap.getValue(PREFIX_EVENT).get());
        friendName = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        time = ParserUtil.parseTimeBlock(argMultimap.getValue(PREFIX_FREETIME).get());
        date = ParserUtil.parseDate(argMultimap.getValue(PREFIX_DATE).get());
        reminder = ParserUtil.parseReminderBoolean(argMultimap.getValue(PREFIX_REMINDER).get());

        return new AddEventCommand(eventName, friendName, time, date, reminder);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}