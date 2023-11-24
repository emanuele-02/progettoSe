package ActionFolder;

import javax.sound.sampled.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class AudioAction {
    private String filePath;

    // Constructor to initialize the AudioAction with a check for an empty string, if is true, then throw an exception
    public AudioAction(String filePath) {
        if (filePath == null || filePath.trim().isEmpty()) {
            throw new IllegalArgumentException("Error: File path cannot be empty or null.");
        }
        this.filePath = filePath;
    }

    

    // Method to execute the audio playback
    public void execute() throws UnsupportedAudioFileException, IOException, LineUnavailableException, InterruptedException, FileNotFoundException {
        // Create a File object with the provided file path
        File audioFile = new File(filePath);

        // If the file does not have a .wav extension, throw an exception
        if (!filePath.toLowerCase().endsWith(".wav")) {
            throw new UnsupportedAudioFileException("Error: Unsupported audio file format. Please provide a .wav file.");
        }

        // If the audio file does not exist, throw an exception
        if (!audioFile.exists()) {
            throw new FileNotFoundException("Error: Audio file does not exist.");
        }

        // Obtain an AudioInputStream from the audio file
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(audioFile);

        // Create a Clip to play the audio
        Clip clip = AudioSystem.getClip();

        // Open the Clip with the AudioInputStream
        clip.open(audioInputStream);

        // Create a CountDownLatch with an initial count of 1
        CountDownLatch latch = new CountDownLatch(1);

        // Add a LineListener to the Clip to detect when the audio playback stops
        clip.addLineListener(event -> {
            if (event.getType() == LineEvent.Type.STOP) {
                System.out.println("Audio playback stopped.");
                // Count down the latch when the playback stops
                latch.countDown();
            }
        });

        // Start the audio playback
        clip.start();

        // Wait until the latch is counted down to 0, indicating the playback has stopped
        latch.await();

        // Close the Clip and AudioInputStream to release resources
        clip.close();
        audioInputStream.close();
    }

}