package seedu.address.model.person.timetable;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.DayOfWeek;

/**
 * Represents a block of time in a day, with a day of the week and start and end times.
 * Guarantees: immutable; is valid as declared in {@link #isValidTimeBlock(String)}}
 */
public class TimeBlock implements Comparable<TimeBlock> {

    public static final String MESSAGE_CONSTRAINTS = "Input should be in the format 'DAY HHMM HHMM', \n"
            + "where 'DAY' is a valid day of the week (e.g., Monday, tuesday, WEDNESDAY), \n"
            + "and 'HHMM' represents a valid 24-hour time format in half-hour blocks (e.g., 0000, 1230, 2300). \n"
            + "Day is case-insensitive. The start time must be before the end time.";

    public static final String VALIDATION_REGEX = "^(?i)(monday|tuesday|wednesday|thursday|friday|saturday|sunday) "
            + "([01]?\\d|2[0-3])(00|30) ([01]?\\d|2[0-3])(00|30)$"; //format: (case-insensitive) day 2359 2359

    public final String timeBlockString;
    private final DayOfWeek day;
    private final HalfHourBlocks timeBlocks;

    /**
     * Constructs a {@code TimeBlock}.
     *
     * @param timeBlockString A valid time.
     */
    public TimeBlock(String timeBlockString) {
        requireNonNull(timeBlockString);
        checkArgument(isValidTimeBlock(timeBlockString), MESSAGE_CONSTRAINTS);

        String[] parts = timeBlockString.split(" ");
        this.day = DayOfWeek.valueOf(parts[0].toUpperCase());
        int startBlock = Integer.parseInt(parts[1]) / 100 * 2 + (Integer.parseInt(parts[1]) % 100) / 30;
        int endBlock = Integer.parseInt(parts[2]) / 100 * 2 + (Integer.parseInt(parts[2]) % 100) / 30;
        this.timeBlocks = new HalfHourBlocks(startBlock, endBlock);

        //Capitalize the first letter of the day      
        String formattedDay = parts[0].substring(0, 1).toUpperCase() + parts[0].substring(1).toLowerCase();
        this.timeBlockString = formattedDay + " " + parts[1] + " " + parts[2];
    }

    /**
     * Adds a new time to the timetable.
     * @param timeBlockString The string representation of the time block to add.
     */
    public void addTime(String timeBlockString) {
        requireNonNull(timeBlockString);
        checkArgument(isValidTimeBlock(timeBlockString), MESSAGE_CONSTRAINTS);

        String[] parts = timeBlockString.split(" ");
        int startBlock = Integer.parseInt(parts[1]) / 100 * 2 + (Integer.parseInt(parts[1]) % 100) / 30;
        int endBlock = Integer.parseInt(parts[2]) / 100 * 2 + (Integer.parseInt(parts[2]) % 100) / 30;
        this.timeBlocks.addTime(startBlock, endBlock);
    }

    /**
     * Returns true if a given string is a valid TimeBlock.
     */
    public static boolean isValidTimeBlock(String test) {
        if (!test.matches(VALIDATION_REGEX)) {
            return false;
        }

        String[] parts = test.split(" ");
        int startTime = Integer.parseInt(parts[1]);
        int endTime = Integer.parseInt(parts[2]);
        return startTime < endTime;
    }

    /**
     * Compares this TimeBlock instance with another instance.
     * The comparison is primarily based on the day of the week, followed by the start time, and then the end time.
     *
     * @param other The other TimeBlock instance to compare against.
     * @return A negative integer, zero, or a positive integer as this TimeBlock is less than, equal to, or greater
     *      than the specified TimeBlock.
     */
    @Override
    public int compareTo(TimeBlock other) {
        if (this.day.compareTo(other.day) != 0) {
            return this.day.compareTo(other.day);
        }
        // You can further refine this if needed
        return this.timeBlocks.toString().compareTo(other.timeBlocks.toString());
    }

    /**
     * Compares the start time of this TimeBlock with another TimeBlock's start time.
     *
     * @param other The other TimeBlock instance to compare against.
     * @return A negative integer, zero, or a positive integer as this TimeBlock's start time is less than,
     *         equal to, or greater than the specified TimeBlock's start time.
     */
    public int compareByStartTime(TimeBlock other) {
        return this.timeBlocks.compareTo(other.timeBlocks);
    }

    /**
     * Checks if the current TimeBlock overlaps with another TimeBlock.
     * If there's an overlap, returns a new TimeBlock representing the overlapping period.
     *
     * @param other The other TimeBlock to check against.
     * @return An Optional containing the overlapping TimeBlock if it exists, or an empty Optional otherwise.
     */
    public TimeBlock overlap(TimeBlock other) {
        if (this.day != other.day) {
            return null;
        }

        if (this.timeBlocks.overlaps(other.timeBlocks)) {
            HalfHourBlocks overlapBlocks = this.timeBlocks.getOverlap(other.timeBlocks);
            // You can convert overlapBlocks back to a string representation if needed
            return new TimeBlock(day + " " + overlapBlocks.toString());
        }

        return null;
    }

    /**
     * Checks if the current {@code TimeBlock} overlaps with the specified {@code TimeBlock}.
     *
     * @param other The other {@code TimeBlock} instance to check for overlap.
     * @return {@code true} if the current {@code TimeBlock} overlaps with the specified {@code TimeBlock},
     *         {@code false} otherwise.
     */
    public boolean isOverlap(TimeBlock other) {
        if (this.day != other.day) {
            return false;
        }

        return this.timeBlocks.overlaps(other.timeBlocks);
    }

    /**
     * Checks if the current TimeBlock is on the specified day.
     *
     * @param day The day to check (e.g., 1 for Monday, 2 for Tuesday, etc.)
     * @return True if the TimeBlock is on the given day, false otherwise.
     */
    public boolean isOnDay(int day) {
        return day == this.day.getValue();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FreeTime)) {
            return false;
        }

        TimeBlock otherTimeBlock = (TimeBlock) other;
        return timeBlockString.equals(otherTimeBlock.timeBlockString);
    }

    @Override
    public int hashCode() {
        return timeBlockString.hashCode();
    }

    @Override
    public String toString() {
        return '[' + timeBlockString + ']';
    }
}
