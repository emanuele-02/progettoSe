package ActionFolder;

import javax.sound.sampled.*;
import java.io.File;
import java.util.concurrent.CountDownLatch;

public class AudioAction implements Action {
    private String filePath;

    // Constructor to initialize the AudioAction with a check for an empty string
    // and invalid format, if is true, then throw an exception
    public AudioAction(String filePath) {

        // If the filepath is null, throw an exception
        if (filePath == null || filePath.trim().isEmpty()) {
            throw new IllegalArgumentException("Error: File path cannot be empty or null.");
        }
        // If the filepath does not have a .wav extension, throw an exception
        else if (!filePath.toLowerCase().endsWith(".wav")) {
            throw new IllegalArgumentException("Error: Unsupported audio file format. Please provide a .wav file.");
        } else
            this.filePath = filePath;
        File audioFile = new File(filePath);
        if (!audioFile.exists()) {
            throw new IllegalArgumentException("Error: Audio file does not exist.");
        }
    }

    // Method to execute the audio playback
    @Override
    public void execute() {
        // Create a File object with the provided file path

        // If the audio file does not exist, throw an exception
        File audioFile = new File(filePath);

        try {
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
                    // System.out.println("Audio playback stopped.");
                    // Count down the latch when the playback stops
                    latch.countDown();
                }
            });

            // Start the audio playback
            clip.start();

            // Wait until the latch is counted down to 0, indicating the playback has
            // stopped
            latch.await();

            // Close the Clip and AudioInputStream to release resources
            clip.close();
            audioInputStream.close();

        } catch (Exception e) {
            // Handle the exception (e.g., log it, print a message, etc.)

            e.printStackTrace(); // Replace this with your desired exception handling logic

        }

    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((filePath == null) ? 0 : filePath.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        AudioAction other = (AudioAction) obj;
        if (filePath == null) {
            if (other.filePath != null)
                return false;
        } else if (!filePath.equals(other.filePath))
            return false;
        return true;
    }
}
