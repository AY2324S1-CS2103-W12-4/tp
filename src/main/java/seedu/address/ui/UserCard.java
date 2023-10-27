package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import seedu.address.model.person.Person;

/**
 * Represents a user card in the GUI.
 * Displays the user's name, phone number, address, email, free times, and tags.
 */
public class UserCard extends UiPart<Region> {

    private static final String FXML = "UserCard.fxml";

    public final Person user;

    @FXML
    private Label userName;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private FlowPane freeTimes;
    @FXML
    private FlowPane tags;

    /**
     * Creates a new UserCard with the given Person object.
     * @param user The Person object to display in the card.
     */
    public UserCard(Person user) {
        super(FXML);
        this.user = user;
        userName.setText(user.getName().fullName);
        phone.setText(user.getPhone().value);
        address.setText(user.getAddress().value);
        email.setText(user.getEmail().value);
        user.getTimeblocks().stream()
                .sorted(Comparator.comparing(freeTime -> freeTime.timeBlockString))
                .forEach(freeTime -> freeTimes.getChildren().add(new Label(freeTime.timeBlockString)));
        user.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }

}
