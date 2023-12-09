package ActionFolder;

import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import CounterFolder.*;

// WriteStringOnFileAction class
public class WriteStringOnFileAction implements Action {
    private String targetFilePath;
    private String stringToWrite;

    // Constructor
    public WriteStringOnFileAction(String targetFilePath, String stringToWrite) {
        if (!targetFilePath.toLowerCase().endsWith(".txt") && !targetFilePath.toLowerCase().endsWith(".text")) {
            throw new IllegalArgumentException("Error: Unsupported file format. Please provide a .txt file.");

        } else {
            this.targetFilePath = targetFilePath;
            this.stringToWrite = stringToWrite;

            File file = new File(targetFilePath);
            if (!file.exists())
                throw new IllegalArgumentException("The file does not exist: ");
        }
    }

    @Override
    public void execute() {

        MapCounter counter = MapCounter.getInstance();
        String substitutedString = counter.substituteVariables(stringToWrite);

        // Write to the file

        try (FileWriter fileWriter = new FileWriter(targetFilePath, true)) {
            fileWriter.write(substitutedString + " ");
        } catch (IOException e) {
            // Handle the IO exception (you can print the stack trace or handle it
            // differently)
            e.printStackTrace();
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((targetFilePath == null) ? 0 : targetFilePath.hashCode());
        result = prime * result + ((stringToWrite == null) ? 0 : stringToWrite.hashCode());
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
        WriteStringOnFileAction other = (WriteStringOnFileAction) obj;
        if (targetFilePath == null) {
            if (other.targetFilePath != null)
                return false;
        } else if (!targetFilePath.equals(other.targetFilePath))
            return false;
        if (stringToWrite == null) {
            if (other.stringToWrite != null)
                return false;
        } else if (!stringToWrite.equals(other.stringToWrite))
            return false;
        return true;
    }

}
