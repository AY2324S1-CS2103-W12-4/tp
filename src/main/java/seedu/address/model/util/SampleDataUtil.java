package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.timetable.TimeBlock;
import seedu.address.model.tag.Tag;
import seedu.address.model.user.ReadOnlyUserData;
import seedu.address.model.user.UserData;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"),
                getTimeBlockSet("Monday 1200 1300", "Tuesday 1200 1300"),
                getTagSet("friends")),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                getTimeBlockSet("Monday 1200 1330", "Wednesday 1100 1400"),
                getTagSet("colleagues", "friends")),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                getTimeBlockSet("Monday 1100 1330", "Tuesday 1130 1300", "Thursday 1200 1300"),
                getTagSet("neighbours")),
            new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                getTimeBlockSet("Monday 1200 1330", "Tuesday 1200 1300"),
                getTagSet("family")),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"),
                getTimeBlockSet("Monday 1200 1300", "Tuesday 1200 1300"),
                getTagSet("classmates")),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"),
                getTimeBlockSet("Monday 1200 1300", "Tuesday 1200 1300"),
                getTagSet("colleagues"))
        };
    }

    public static Person getSampleUser() {
        return new Person(new Name("Me"), new Phone("00000000"), new Email("me@example.com"),
            new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
            getTimeBlockSet("Monday 1200 1300"), getTagSet("me"));
    }
    public static ReadOnlyUserData getSampleUserData() {
        return new UserData(getSampleUser());
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
    }

    /**
     * Returns a set of free times containing the list of strings given.
     */
    public static Set<TimeBlock> getTimeBlockSet(String... strings) {
        return Arrays.stream(strings)
                .map(TimeBlock::new)
                .collect(Collectors.toSet());
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
