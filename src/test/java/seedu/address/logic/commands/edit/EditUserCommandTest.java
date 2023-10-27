package seedu.address.logic.commands.edit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.DESC_USER_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DESC_USER_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.user.User;
import seedu.address.model.user.UserData;
import seedu.address.model.user.UserPrefs;
import seedu.address.testutil.EditUserDescriptorBuilder;
import seedu.address.testutil.UserBuilder;

public class EditUserCommandTest {
    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), new UserData());

    @Test
    public void execute_allFieldsSpecified_success() {
        User newUser = new UserBuilder().build();
        EditUserDescriptor descriptor = new EditUserDescriptorBuilder(newUser).build();
        EditUserCommand editUserCommand = new EditUserCommand(descriptor);

        String expectedMessage = String.format(EditUserCommand.MESSAGE_EDIT_USER_SUCCESS, Messages.format(newUser));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()),
                new UserPrefs(), new UserData());
        expectedModel.setUser(newUser);

        assertCommandSuccess(editUserCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecified_success() {
        User newUser = new UserBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
                .withTags(VALID_TAG_HUSBAND).build();
        EditUserDescriptor descriptor = new EditUserDescriptorBuilder(newUser).withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withTags(VALID_TAG_HUSBAND).build();
        EditUserCommand editUserCommand = new EditUserCommand(descriptor);

        String expectedMessage = String.format(EditUserCommand.MESSAGE_EDIT_USER_SUCCESS, Messages.format(newUser));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()),
                new UserPrefs(), new UserData());
        expectedModel.setUser(newUser);

        assertCommandSuccess(editUserCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateUser_failure() {
        User user = model.getUser();
        EditUserDescriptor descriptor = new EditUserDescriptorBuilder(user).build();
        EditUserCommand editUserCommand = new EditUserCommand(descriptor);

        assertCommandFailure(editUserCommand, model, EditUserCommand.MESSAGE_DUPLICATE_USER);
    }

    @Test
    public void equals() {
        final EditUserCommand standardCommand = new EditUserCommand(DESC_USER_AMY);

        // same values -> returns true
        EditUserDescriptor copyDescriptor = new EditUserDescriptor(DESC_USER_AMY);
        EditUserCommand commandWithSameValues = new EditUserCommand(copyDescriptor);
        assertEquals(standardCommand, commandWithSameValues);

        // same object -> returns true
        assertEquals(standardCommand, standardCommand);

        // null -> returns false
        assertNotEquals(null, standardCommand);

        // different types -> returns false
        assertNotEquals(standardCommand, new ClearCommand());

        // different descriptor -> returns false
        assertNotEquals(standardCommand, new EditUserCommand(DESC_USER_BOB));
    }

    @Test
    public void toStringMethod_nullEditUserDescriptor() {
        EditUserDescriptor editUserDescriptor = new EditUserDescriptor();
        EditUserCommand editUserCommand = new EditUserCommand(editUserDescriptor);
        String expected = EditUserCommand.class.getCanonicalName() + "{" + "editUserDescriptor="
                + editUserDescriptor + "}";
        assertEquals(expected, editUserCommand.toString());
    }

    @Test
    public void toStringMethod_filledEditPersonDescriptor() {
        EditUserDescriptor editUserDescriptor = DESC_USER_AMY;
        EditUserCommand editUserCommand = new EditUserCommand(editUserDescriptor);
        String expected = EditUserCommand.class.getCanonicalName() + "{" + "editUserDescriptor="
                + editUserDescriptor + "}";
        assertEquals(expected, editUserCommand.toString());
    }

}
