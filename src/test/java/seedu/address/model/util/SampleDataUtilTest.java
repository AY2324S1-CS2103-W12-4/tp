package seedu.address.model.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Person;
import seedu.address.model.user.ReadOnlyUserData;

public class SampleDataUtilTest {
    @Test
    public void getSamplePersons_validData_success() {
        Person[] samplePersons = SampleDataUtil.getSamplePersons();
        assertEquals(6, samplePersons.length);
    }

    @Test
    public void getSampleUser_validData_success() {
        Person user = SampleDataUtil.getSampleUser();
        assertEquals("Me", user.getName().fullName);
        assertEquals(1, user.getTimeblocks().size());
        assertEquals(1, user.getTags().size());
    }

    @Test
    public void getSampleUserData_validData_success() {
        ReadOnlyUserData userData = SampleDataUtil.getSampleUserData();
        Person user = userData.getUser();
        assertEquals("Me", user.getName().fullName);
        assertEquals(1, user.getTimeblocks().size());
        assertEquals(1, user.getTags().size());
    }

    @Test
    public void getSampleAddressBook_validData_success() {
        ReadOnlyAddressBook addressBook = SampleDataUtil.getSampleAddressBook();
        assertEquals(6, addressBook.getPersonList().size());
    }

    @Test
    public void getFreeTimeSet_validData_success() {
        String[] freeTimes = { "Monday 1200 1300", "Tuesday 1200 1300", "Wednesday 1000 1100" };
        assertEquals(3, SampleDataUtil.getTimeBlockSet(freeTimes).size());
    }

    @Test
    public void getTagSet_validData_success() {
        String[] tags = { "friends", "colleagues", "family" };
        assertEquals(3, SampleDataUtil.getTagSet(tags).size());
    }
}
