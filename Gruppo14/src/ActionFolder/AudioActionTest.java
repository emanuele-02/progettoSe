package ActionFolder;
import org.junit.jupiter.api.Test;

public class AudioActionTest {
    @Test
    void testExecute() {
        // Defiine the path of the audio file
        String filePath = "C:\\Users\\giolu\\Downloads\\TestAudioAction.wav";

        // Create the instance of audioAction
        AudioAction audioAction = new AudioAction(filePath);

        //execute the action
        audioAction.execute();
        try {
            Thread.sleep(5000); // wait 5 second
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        
    }

}