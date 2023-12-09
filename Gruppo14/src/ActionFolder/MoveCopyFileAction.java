package ActionFolder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class MoveCopyFileAction implements Action {
    private OperationFileType operation;
    private String targetFileName;
    private String targetDirectory;
    private String destinationDirectory;

    // Constructor
    public MoveCopyFileAction(OperationFileType operation, String targetFileName, String targetDirectory,
            String destinationDirectory) {
        this.operation = operation;
        this.targetFileName = targetFileName;
        this.targetDirectory = targetDirectory;
        this.destinationDirectory = destinationDirectory;

        Path sourcePath = Paths.get(targetDirectory, targetFileName);

        // Various checks to verify the existence of the file and direcotry
        if (!Files.exists(sourcePath)) {
            throw new IllegalArgumentException("Invalid source file: " + sourcePath);
        }

        if (operation != OperationFileType.COPY && operation != OperationFileType.MOVE) {
            throw new IllegalArgumentException("Invalid operation type: " + operation);
        }

    }

    // execute() method implemented from the Action interface
    @Override
    public void execute() {
        // Build full paths for the source and destination files
        Path sourcePath = Paths.get(targetDirectory, targetFileName);
        Path destinationPath = Paths.get(destinationDirectory, targetFileName);

        try {
            // Check the operation type and perform copy or move
            if (operation == OperationFileType.COPY) {
                // Copy the file to the destination with the option to replace existing files
                Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
            } else if (operation == OperationFileType.MOVE) {
                // Move the file to the destination with the option to replace existing files
                Files.move(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
            } else {
                // Throw an exception for unsupported operation type
                throw new IllegalArgumentException("Unsupported operation type: " + operation);
            }

        } catch (IOException e) {
            // Handle IO exception (you can print the stack trace or handle it differently)
            e.printStackTrace();
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((operation == null) ? 0 : operation.hashCode());
        result = prime * result + ((targetFileName == null) ? 0 : targetFileName.hashCode());
        result = prime * result + ((targetDirectory == null) ? 0 : targetDirectory.hashCode());
        result = prime * result + ((destinationDirectory == null) ? 0 : destinationDirectory.hashCode());
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
        MoveCopyFileAction other = (MoveCopyFileAction) obj;
        if (operation != other.operation)
            return false;
        if (targetFileName == null) {
            if (other.targetFileName != null)
                return false;
        } else if (!targetFileName.equals(other.targetFileName))
            return false;
        if (targetDirectory == null) {
            if (other.targetDirectory != null)
                return false;
        } else if (!targetDirectory.equals(other.targetDirectory))
            return false;
        if (destinationDirectory == null) {
            if (other.destinationDirectory != null)
                return false;
        } else if (!destinationDirectory.equals(other.destinationDirectory))
            return false;
        return true;
    }

}