package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javafx.util.converter.LocalDateTimeStringConverter;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.timetable.FreeTime;
import seedu.address.model.person.timetable.TimeBlock;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String eventName} into a {@code EventName}.
     * Leading and trailing whitespaces will be trimmed.
     * @param eventName The name of the event.
     * @return A {@code String} object representing the name of the event.
     * @throws ParseException if the given {@code eventName} is invalid.
     */
    public static String parseEventName(String eventName) throws ParseException {
        requireNonNull(eventName);
        String trimmedEventName = eventName.trim();
        if (!Name.isValidName(trimmedEventName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return trimmedEventName;
    }

    /**
     * Parses a {@code String phone} into a {@code Phone}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static Phone parsePhone(String phone) throws ParseException {
        requireNonNull(phone);
        String trimmedPhone = phone.trim();
        if (!Phone.isValidPhone(trimmedPhone)) {
            throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
        }
        return new Phone(trimmedPhone);
    }

    /**
     * Parses a {@code String address} into an {@code Address}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code address} is invalid.
     */
    public static Address parseAddress(String address) throws ParseException {
        requireNonNull(address);
        String trimmedAddress = address.trim();
        if (!Address.isValidAddress(trimmedAddress)) {
            throw new ParseException(Address.MESSAGE_CONSTRAINTS);
        }
        return new Address(trimmedAddress);
    }

    /**
     * Parses a {@code String email} into an {@code Email}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static Email parseEmail(String email) throws ParseException {
        requireNonNull(email);
        String trimmedEmail = email.trim();
        if (!Email.isValidEmail(trimmedEmail)) {
            throw new ParseException(Email.MESSAGE_CONSTRAINTS);
        }
        return new Email(trimmedEmail);
    }

    /**
     * Converts a {@code String timeblock} into a {@code TimeBlock}.
     * @param timeblock
     * @return A TimeBlock object representing the timeblock.
     * @throws ParseException
     */
    public static TimeBlock parseTimeBlock(String timeblock) throws ParseException {
        requireNonNull(timeblock);
        String trimmedTimeBlock = timeblock.trim();
        if (!TimeBlock.isValidTimeBlock(trimmedTimeBlock)) {
            throw new ParseException(TimeBlock.MESSAGE_CONSTRAINTS);
        }

        return new TimeBlock(trimmedTimeBlock);
    }

    /**
     * Parses a {@code String freeTime} into a {@code FreeTime}.
     * Leading and trailing whitespaces will be trimmed.
     * @throws ParseException if the given {@code freeTime} is invalid.
     */
    public static FreeTime parseFreeTime(String freeTime) throws ParseException {
        requireNonNull(freeTime);
        String trimmedFreeTime = freeTime.trim();
        if (!FreeTime.isValidFreeTime(trimmedFreeTime)) {
            throw new ParseException(FreeTime.MESSAGE_CONSTRAINTS);
        }
        return new FreeTime(trimmedFreeTime);
    }

    /**
     * Parses a string representation of a reminder into a boolean value.
     * The string must be either "yes" or "no" (case-insensitive).

     * @param reminder A string representation of a reminder.
     * @return A boolean value representing whether the reminder is set or not.
     * @throws ParseException If the input string is not "yes" or "no".
     */
    public static boolean parseReminderBoolean(String reminder) throws ParseException {
        requireNonNull(reminder);
        String trimmedReminder = reminder.trim().toLowerCase();
        if (!trimmedReminder.equals("yes") && !trimmedReminder.equals("no")) {
            throw new ParseException("Reminder must be either yes or no");
        }
        return Boolean.parseBoolean(trimmedReminder);
    }

    /**
     * Parses a string representation of a Date into a LocalDateTime object.
     * @param date A string representation of a date.
     * @return A LocalDateTime object representing the date.
     * @throws ParseException If the input string is not a valid date.
     */
    public static LocalDate parseDate(String date) throws ParseException {
        requireNonNull(date);
        String trimmedDate = date.trim().toLowerCase();
        try {
            return LocalDate.parse(trimmedDate);
        } catch (Exception e) {
            throw new ParseException("Date must be in the format YYYY-MM-DD");
        }
    }
    
    /**
     * Parses {@code Collection<String> freeTimes} into a {@code Set<FreeTime>}.
     * @param freeTimes A collection of strings representing free times.
     * @return A set of FreeTime objects representing the free times.
     * @throws ParseException If the input string is not a valid free time.
     */
    public static Set<FreeTime> parseFreeTimes(Collection<String> freeTimes) throws ParseException {
        requireNonNull(freeTimes);
        final Set<FreeTime> freeTimeSet = new HashSet<>();
        for (String freeTimeName : freeTimes) {
            freeTimeSet.add(parseFreeTime(freeTimeName));
        }
        return freeTimeSet;
    }

    /**
     * Parses {@code Collection<String> timeBlocks} into a {@code Set<TimeBlock>}.
     * @param timeBlocks A collection of strings representing time blocks.
     * @return A set of TimeBlock objects representing the time blocks.
     * @throws ParseException If the input string is not a valid time block.
     */
    public static Set<TimeBlock> parseTimeBlocks(Collection<String> timeBlocks) throws ParseException {
        requireNonNull(timeBlocks);
        final Set<TimeBlock> timeBlockSet = new HashSet<>();
        for (String timeBlockName : timeBlocks) {
            timeBlockSet.add(parseTimeBlock(timeBlockName));
        }
        return timeBlockSet;
    }

    /**
     * Parses a {@code String tag} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static Tag parseTag(String tag) throws ParseException {
        requireNonNull(tag);
        String trimmedTag = tag.trim();
        if (!Tag.isValidTagName(trimmedTag)) {
            throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new Tag(trimmedTag);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>}.
     */
    public static Set<Tag> parseTags(Collection<String> tags) throws ParseException {
        requireNonNull(tags);
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(parseTag(tagName));
        }
        return tagSet;
    }
}
