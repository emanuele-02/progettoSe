package TriggerFolder;

import java.io.File;

public class FileDirectoryTrigger implements Trigger {

    private String targetFileName;
    private String targetDirectory;

    // Constructor
    public FileDirectoryTrigger(String targetFileName, String targetDirectory) {
        this.targetFileName = targetFileName;
        this.targetDirectory = targetDirectory;
    }

    // Method to check the trigger condition
    @Override
    public boolean checkTrigger() {
        try {
            // Construct the full path to the target file
            String fullPath = targetDirectory + File.separator + targetFileName;

            // Check if the file exists in the specified directory
            File file = new File(fullPath);
            return file.exists() && file.isFile();
        } catch (Exception e) {
            // Handle exceptions
            e.printStackTrace();
            return false;
        }
    }

    // Override execute method from the Trigger interface

}