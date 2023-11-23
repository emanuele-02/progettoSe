package ActionFolder;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class AudioAction {
    //  path of the audio file to be played
    private String filePath;
    // costructor 
    public AudioAction(String filePath) {
        this.filePath = filePath;
    }
    // execute method
    public void execute() {
        try {
            // Create a File object with the specified file pat
            File audioFile = new File(filePath);
            // Obtain an AudioInputStream from the audio file
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(audioFile);
             // Create a Clip to play the audio
            Clip clip = AudioSystem.getClip();
            // Create a CountDownLatch to wait for the playback to finish
            clip.open(audioInputStream);

            CountDownLatch latch = new CountDownLatch(1);

            clip.addLineListener(event -> {
                if (event.getType() == LineEvent.Type.STOP) {
                    System.out.println("Audio playback stopped.");
                    latch.countDown();
                }
            });

            clip.start();

            // Create a CountDownLatch to wait for the playback to finish
            latch.await();
            clip.close();
            audioInputStream.close();

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException | InterruptedException e) {
            e.printStackTrace();
        }
    }

 
}