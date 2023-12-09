package TriggerFolder;

import java.io.File;

public class FileSizeTrigger implements Trigger {

    private String targetFilePath;
    private long targetSize; // Size in kilobytes

    // Constructor
    public FileSizeTrigger(String targetFilePath, long targetSize) {
        // Check if the file exists
        File file = new File(targetFilePath);
        if (!file.exists() || !file.isFile()) {
            throw new IllegalArgumentException("File does not exist or is not a regular file: " + targetFilePath);
        }

        this.targetFilePath = targetFilePath;
        this.targetSize = targetSize;
    }

    // Method to check the trigger condition
    @Override
    public boolean checkTrigger() {
        try {
            // Get the size of the file in kilobytes
            long fileSizeInKB = new File(targetFilePath).length() / 1024;

            // Check if the file size is larger than the target size
            return fileSizeInKB > targetSize;

        } catch (Exception e) {
            // Handle exceptions
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((targetFilePath == null) ? 0 : targetFilePath.hashCode());
        result = prime * result + (int) (targetSize ^ (targetSize >>> 32));
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
        FileSizeTrigger other = (FileSizeTrigger) obj;
        if (targetFilePath == null) {
            if (other.targetFilePath != null)
                return false;
        } else if (!targetFilePath.equals(other.targetFilePath))
            return false;
        if (targetSize != other.targetSize)
            return false;
        return true;
    }

}
