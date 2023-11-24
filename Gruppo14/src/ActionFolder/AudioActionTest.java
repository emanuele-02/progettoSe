package ActionFolder;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.FileNotFoundException;
import javax.sound.sampled.UnsupportedAudioFileException;

    public class AudioActionTest {
        @Test
        void testValidExecute() {
            // Test with a valid .wav file
            // Define the path of the audio file
            String validFilePath = "C:\\Users\\simon\\Downloads\\TestAudioAction.wav";
            
            // Create on istance of AudioAction whit a valid path
            AudioAction validAudioAction = new AudioAction(validFilePath);
            // Assert that executing the validAudioAction does not throw any exception
            assertDoesNotThrow(() -> validAudioAction.execute());

        }
        @Test
        void testFileNotExistExecute() {
            // Test with a non-existent file
            // Define the path of the audio file
            String nonExistentFilePath = "C:\\Users\\simon\\Downloads\\NonExistentAudio.wav";
            
            // Create an instance of AudiiAction whit a not existing
            AudioAction nonExistentAudioAction = new AudioAction(nonExistentFilePath);
             // Assert that executing nonExistentAudioAction throws a FileNotFoundException
            assertThrows(FileNotFoundException.class, nonExistentAudioAction::execute);
        
        }

        @Test
        void testInvalidFormatExecute() {
            // Test with an invalid file format (not .wav)
            // Define the path of the audio file
            String invalidFormatFilePath = "C:\\Users\\simon\\Downloads\\InvalidFormatAudio.mp3";

            // create an istance of AudioAction whit an invalid path
            AudioAction invalidFormatAudioAction = new AudioAction(invalidFormatFilePath);
             // Assert that executing invalidFormatAudioAction throws an UnsupportedAudioFileException
            assertThrows(UnsupportedAudioFileException.class, invalidFormatAudioAction::execute);
        }

         // Test for empty file path (should throw IllegalArgumentException)
    @Test
    void testEmptyFilePath() {
        // Define an empty file path
        String emptyFilePath = "";

        // Create an instance of AudioAction with an empty file path
        // Assert that executing this action throws an IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> new AudioAction(emptyFilePath));
    }
}

