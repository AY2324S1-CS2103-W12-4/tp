package seedu.address.model.person.timetable;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents a DatedEvent in the application.
 * Encapsulates an event that is not recurring on a weekly basis.
 * Contains details such as the event name, time block, date, and reminder settings.
 */
public class DatedEvent implements Comparable<DatedEvent> {
    public static final String MESSAGE_CONSTRAINTS =
            "Input should be in the format 'name YYYY-MM-DD HHMM HHMM', \n"
                    + "where:\n"
                    + "- 'name' represents the name and should not contain spaces.\n"
                    + "- 'YYYY-MM-DD' represents a date (e.g., '2023-10-24').\n"
                    + "- 'HHMM' represents a valid 24-hour time format in half-hour blocks (e.g., 0000, 1230, 2300). \n"
                    + "- The first 'HHMM' represents the starting time (e.g., '0830' for 08:30 AM).\n"
                    + "- The second 'HHMM' represents the ending time (e.g., '1730' for 05:30 PM).\n";
    public static final String DATE_TIME_VALIDATION_REGEX = "^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])$";
    private String name;
    private TimeBlock timeBlock; //encapsulates the time period this event takes
    private LocalDate date;
    private boolean hasReminder;
    private String dateTimeFormatterPattern = "yyyy-MM-dd";

    /**
     * Initializes a new DatedEvent with the provided details.
     *
     * @param name The name of the event.
     * @param timeBlockString The time block for the event.
     * @param dateString The date of the event in the format "YYYY-MM-DD".
     * @param reminder A flag indicating if a reminder is set for this event.
     */
    public DatedEvent(String name, String timeBlockString, String dateString, boolean reminder) {
        requireNonNull(name);
        checkArgument(isValidDateTimeString(dateString), MESSAGE_CONSTRAINTS);
        timeBlock = new TimeBlock(timeBlockString);
        this.name = name;
        this.hasReminder = reminder;
        this.date = LocalDate.parse(dateString, DateTimeFormatter.ofPattern(dateTimeFormatterPattern));
    }

    /**
     * Initializes a new DatedEvent by parsing the provided input string.
     *
     * @param unparsedInput The input string in the format "name YYYY-MM-DD HHMM HHMM".
     */
    public DatedEvent(String unparsedInput) { // e.g., "meet Andre 2023-10-10 1030 1130"
        requireNonNull(unparsedInput);

        // Regex to capture the name, date, start time, and end time
        String regex = "^(.+) (\\d{4}-\\d{2}-\\d{2}) (\\d{4}) (\\d{4})$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(unparsedInput);

        checkArgument(matcher.matches(), MESSAGE_CONSTRAINTS);

        // Extracting components using the matcher
        String nameString = matcher.group(1); // event name
        String dateString = matcher.group(2); // date
        String startTime = matcher.group(3); // start time
        String endTime = matcher.group(4); // end time

        checkArgument(isValidDateTimeString(dateString), MESSAGE_CONSTRAINTS);

        // Parse the date
        this.date = LocalDate.parse(dateString, DateTimeFormatter.ofPattern(dateTimeFormatterPattern));
        String dayOfWeek = date.getDayOfWeek().name();

        // Create the time block
        this.timeBlock = new TimeBlock(dayOfWeek + " " + startTime + " " + endTime);

        this.name = nameString;
        // Since the unparsed input constructor does not provide a reminder flag,
        // we default it to false or any other default value you may want.
        this.hasReminder = false;
    }

    /**
     * Checks if the provided date time string is valid.
     *
     * @param test The date time string to check.
     * @return true if the string is a valid date time format, false otherwise.
     */
    public static boolean isValidDateTimeString(String test) {
        return test.matches(DATE_TIME_VALIDATION_REGEX);
    }

    /**
     * Returns a format of DateTime that can be stored in a Json File.
     *
     * @return Json formatted String.
     */
    public String toJsonString() {
        return "{"
                + "\"name\": \"" + name + "\","
                + "\"date\": \"" + date.format(DateTimeFormatter.ofPattern(dateTimeFormatterPattern)) + "\","
                + "\"timeBlock\": \"" + timeBlock.toString() + "\","
                + "\"hasReminder\": " + hasReminder
                + "}";
    }

    /**
     * Compares this DatedEvent instance with another instance.
     * The comparison is primarily based on the event's date. If the dates are the same,
     * the comparison proceeds to the start time of the associated TimeBlock.
     *
     * @param other The other DatedEvent instance to compare against.
     * @return A negative integer, zero, or a positive integer as this DatedEvent is less than,
     *         equal to, or greater than the specified DatedEvent.
     */
    @Override
    public int compareTo(DatedEvent other) {
        // First, compare the dates.
        int dateComparison = this.date.compareTo(other.date);
        if (dateComparison != 0) {
            return dateComparison;
        }

        // If the dates are the same, compare the time blocks.
        return this.timeBlock.compareByStartTime(other.timeBlock);
    }

    @Override
    public String toString() {
        return "Event Name: " + name + "\n"
                + "Date: " + date.format(DateTimeFormatter.ofPattern(dateTimeFormatterPattern)) + "\n"
                + "Time: " + timeBlock.toString() + "\n"
                + "Reminder: " + (hasReminder ? "Enabled" : "Disabled");

    }
}
