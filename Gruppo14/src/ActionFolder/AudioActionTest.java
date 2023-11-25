package ActionFolder;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AudioActionTest {
    private static final String VALID_FILE_PATH = "TestAudioAction.wav";
    private static final String NON_EXISTENT_FILE_PATH = "NotExisting.wav";
    private static final String INVALID_FORMAT_FILE_PATH = "TestAudioAction.mp3";
    private static final String EMPTY_FILE_PATH = "";

    @Test
    void testValidExecute() {
        AudioAction validAudioAction = new AudioAction(VALID_FILE_PATH);
        assertDoesNotThrow(validAudioAction::execute);
    }

    @Test
    void testFileNotExisting(){
        AudioAction notExistingAudioAction = new AudioAction(NON_EXISTENT_FILE_PATH);
        assertThrows(IllegalArgumentException.class, notExistingAudioAction::execute);

    }

    @Test
    void testInvalidFormatExecute() {
        assertThrows(IllegalArgumentException.class,() -> new AudioAction (INVALID_FORMAT_FILE_PATH));
    }

    @Test
    void testEmptyFilePath() {
        assertThrows(IllegalArgumentException.class, () -> new AudioAction(EMPTY_FILE_PATH));
    }
}
