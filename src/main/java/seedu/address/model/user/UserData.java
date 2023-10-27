package seedu.address.model.user;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Objects;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.timetable.DatedEvent;

/**
 * Represents a User in the address book.
 */
public class UserData implements ReadOnlyUserData {

    private User user = new User();
    private final ObservableList<User> internalList = FXCollections.observableArrayList();
    private final ObservableList<User> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);


    public UserData() {};

    public UserData(User user) {
        this.user = user;
    }

    public UserData(ReadOnlyUserData userData) {
        this(userData.getUser());
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setDatedEvents(ArrayList<DatedEvent> datedEvents) {
        this.user.setDatedEvents(datedEvents);
    }

    /**
     * Replaces the contents of the user with {@code neData}.
     * {@code newData} must not be null.
     */
    public void resetData(ReadOnlyUserData newData) {
        requireNonNull(newData);
        this.setUser(newData.getUser());
        this.setDatedEvents(newData.getDatedEvents());
    }

    public ObservableList<User> getUserView() {
        return internalUnmodifiableList;
    }

    @Override
    public User getUser() {
        return user;
    }

    @Override
    public ArrayList<DatedEvent> getDatedEvents() {
        return user.getDatedEvents();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UserData)) {
            return false;
        }

        UserData otherUserData = (UserData) other;
        return user.equals(otherUserData.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("User : " + user.toString());
        return sb.toString();
    }

}
