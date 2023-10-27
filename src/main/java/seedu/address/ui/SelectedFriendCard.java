package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import seedu.address.model.person.Person;

/**
 * The {@code SelectedFriendCard} class represents a card that displays detailed information
 * about a selected friend (person).
 * It is typically used to display the information of a friend when selected in the user interface.
 */
public class SelectedFriendCard extends UiPart<Region> {
    private static final String FXML = "SelectedFriendCard.fxml";

    public final Person selectedFriend;

    @javafx.fxml.FXML
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
     * Constructs a new {@code SelectedFriendCard} with the specified selected friend.
     *
     * @param selectedFriend The selected friend whose information is to be displayed on this card.
     */
    public SelectedFriendCard(Person selectedFriend) {
        super(FXML);
        this.selectedFriend = selectedFriend;
        userName.setText(selectedFriend.getName().fullName);
        phone.setText(selectedFriend.getPhone().value);
        address.setText(selectedFriend.getAddress().value);
        email.setText(selectedFriend.getEmail().value);
        selectedFriend.getTimeblocks().stream()
                .sorted(Comparator.comparing(freeTime -> freeTime.timeBlockString))
                .forEach(freeTime -> freeTimes.getChildren().add(new Label(freeTime.timeBlockString)));
        selectedFriend.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }
}
