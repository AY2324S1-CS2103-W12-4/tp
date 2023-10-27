package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalUsers.ALICE;
import static seedu.address.testutil.TypicalUsers.AMY;
import static seedu.address.testutil.TypicalUsers.BENSON;
import static seedu.address.testutil.TypicalUsers.BOB;
import static seedu.address.testutil.TypicalUsers.CARL;
import static seedu.address.testutil.TypicalUsers.DANIEL;
import static seedu.address.testutil.TypicalUsers.ELLE;
import static seedu.address.testutil.TypicalUsers.FIONA;
import static seedu.address.testutil.TypicalUsers.GEORGE;
import static seedu.address.testutil.TypicalUsers.JAMES;
import static seedu.address.testutil.TypicalUsers.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.user.UserData;
import seedu.address.model.user.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) for {@code CommonFreeTimeCommand}.
 */
public class CommonFreetimeCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), new UserData());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs(), new UserData());

    @BeforeEach
    public void setUp() {
        model.setUser(AMY);
        expectedModel.setUser(AMY);
    }

    @Test
    public void execute_userNoFreetime_failure() {
        model.setUser(BOB);
        CommonFreetimeCommand commonFreetimeCommand = new CommonFreetimeCommand();
        assertCommandFailure(commonFreetimeCommand, model, CommonFreetimeCommand.MESSAGE_NO_FREE_TIME);
    }

    @Test
    public void execute_nameNull_success() {
        CommonFreetimeCommand commonFreetimeCommand = new CommonFreetimeCommand();
        String expectedMessage = CommonFreetimeCommand.MESSAGE_COMMON_FREETIME_SUCCESS
                + ALICE.getName().toString() + " is free at " + "[Monday 1200 1300]" + "\n"
                + BENSON.getName().toString() + " is free at " + "[Monday 1200 1300]" + "\n"
                + CARL.getName().toString() + " is free at " + "[Monday 1200 1300]" + "\n"
                + DANIEL.getName().toString() + " is free at " + "[Monday 1200 1300]" + "\n"
                + ELLE.getName().toString() + " is free at " + "[Monday 1200 1300]" + "\n"
                + FIONA.getName().toString() + " is free at " + "[Monday 1200 1300]" + "\n"
                + GEORGE.getName().toString() + " is free at " + "[Monday 1200 1300]" + "\n";
        assertCommandSuccess(commonFreetimeCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_nameFriend_success() {
        CommonFreetimeCommand commonFreetimeCommand = new CommonFreetimeCommand(ALICE.getName());
        String expectedMessage = CommonFreetimeCommand.MESSAGE_COMMON_FREETIME_SUCCESS
                + ALICE.getName().toString() + " is free at " + "[Monday 1200 1300]" + "\n";
        assertCommandSuccess(commonFreetimeCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noOverlapContact_failure() {
        model.setUser(JAMES);
        expectedModel.setUser(JAMES);
        CommonFreetimeCommand commonFreetimeCommand = new CommonFreetimeCommand();
        assertCommandFailure(commonFreetimeCommand, model, CommonFreetimeCommand.MESSAGE_NO_CONTACTS);
    }

    @Test
    public void execute_noOverlapFriend_failure() {
        model.setUser(JAMES);
        expectedModel.setUser(JAMES);
        CommonFreetimeCommand commonFreetimeCommand = new CommonFreetimeCommand(ALICE.getName());
        assertCommandFailure(commonFreetimeCommand, model, CommonFreetimeCommand.createNoOverlapFriendMessage(ALICE));
    }

    @Test
    public void equals() {
        final CommonFreetimeCommand standardCommand = new CommonFreetimeCommand(AMY.getName());

        final CommonFreetimeCommand standardAllCommand = new CommonFreetimeCommand();
        // same values -> returns true
        CommonFreetimeCommand commandWithSameValues = new CommonFreetimeCommand(AMY.getName());
        assertEquals(standardCommand, commandWithSameValues);

        // same object -> returns true
        assertEquals(standardCommand, standardCommand);

        assertEquals(new CommonFreetimeCommand(), standardAllCommand);

        // null -> returns false
        assertNotEquals(null, standardCommand);

        // different types -> returns false
        assertNotEquals(standardCommand, new ClearCommand());

        // different descriptor -> returns false
        assertNotEquals(standardCommand, new CommonFreetimeCommand(ALICE.getName()));
    }

    @Test
    public void toStringMethod() {
        CommonFreetimeCommand commonFreetimeCommandAlice = new CommonFreetimeCommand(ALICE.getName());
        String expected = CommonFreetimeCommand.class.getCanonicalName() + "{name=" + ALICE.getName() + "}";
        assertEquals(expected, commonFreetimeCommandAlice.toString());
    }
}
