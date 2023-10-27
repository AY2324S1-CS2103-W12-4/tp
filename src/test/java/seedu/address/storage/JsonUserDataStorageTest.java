package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.user.ReadOnlyUserData;
import seedu.address.model.user.UserData;
import seedu.address.testutil.UserBuilder;

public class JsonUserDataStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonUserDataStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void constructor_validPath_success() {
        Path filePath = Paths.get("TypicalUserData.json");
        JsonUserDataStorage userDataStorage = new JsonUserDataStorage(filePath);
        assertEquals(filePath, userDataStorage.getUserDataFilePath());
    }

    @Test
    public void readUserData_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readUserData(null));
    }

    public Optional<ReadOnlyUserData> readUserData(String userDataFileInTestDataFolder) throws DataLoadingException {
        Path userDataFilePath = addToTestDataPathIfNotNull(userDataFileInTestDataFolder);
        return new JsonUserDataStorage(userDataFilePath).readUserData(userDataFilePath);
    }

    private Path addToTestDataPathIfNotNull(String userDataFileInTestDataFolder) {
        return userDataFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(userDataFileInTestDataFolder)
                : null;
    }

    @Test
    public void readUserData_missingFile_emptyResult() throws DataLoadingException {
        assertFalse(readUserData("NonExistentFile.json").isPresent());
    }

    @Test
    public void readUserData_notJsonFormat_exceptionThrown() {
        assertThrows(DataLoadingException.class, () -> readUserData("NotJsonFormatUserData.json"));
    }

    @Test
    public void readUserData_illegalValue_exceptionThrown() {
        assertThrows(DataLoadingException.class, () -> readUserData("InvalidUserData.json"));
    }

    @Test
    public void readUserData_successfullyRead() throws DataLoadingException {
        ReadOnlyUserData expected = getTypicalUserData();
        ReadOnlyUserData actual = readUserData("TypicalUserData.json").get();
        assertEquals(expected, actual);
    }

    private UserData getTypicalUserData() {
        UserData userData = new UserData();
        userData.setUser(new UserBuilder().build());
        return userData;
    }

    @Test
    public void readUserData_extraValuesInFile_extraValuesIgnored() throws DataLoadingException {
        ReadOnlyUserData expected = getTypicalUserData();
        ReadOnlyUserData actual = readUserData("ExtraValuesUserData.json").get();
        assertEquals(expected, actual);
    }

    @Test
    public void saveData_nullData_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveUserData(null, "SomeFile.json"));
    }

    @Test
    public void saveData_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveUserData(new UserData(), null));
    }

    private void saveUserData(UserData userData, String dataFileInTestDataFolder) {
        Path userDataFilePath = addToTestDataPathIfNotNull(dataFileInTestDataFolder);
        try {
            new JsonUserDataStorage(userDataFilePath).saveUserData(userData);
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveData_success() throws DataLoadingException, IOException {
        UserData original = new UserData();
        original.setUser(new UserBuilder().build());

        Path dataFilePath = testFolder.resolve("TempData.json");
        JsonUserDataStorage jsonUserDataStorage = new JsonUserDataStorage(dataFilePath);

        jsonUserDataStorage.saveUserData(original);
        ReadOnlyUserData readBack = jsonUserDataStorage.readUserData().get();
        assertEquals(original, readBack);

        original.setUser(new UserBuilder().withName("Bobby").build());
        jsonUserDataStorage.saveUserData(original);
        readBack = jsonUserDataStorage.readUserData().get();
        assertEquals(original, readBack);
    }

}
