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
    public MoveCopyFileAction(OperationFileType operation, String targetFileName, String targetDirectory, String destinationDirectory) {
        this.operation = operation;
        this.targetFileName = targetFileName;
        this.targetDirectory = targetDirectory;
        this.destinationDirectory = destinationDirectory;
    }

    // execute() method implemented from the Action interface
    @Override
    public void execute() {
        // Build full paths for the source and destination files
        Path sourcePath = Paths.get(targetDirectory, targetFileName);
        if (!Files.exists(sourcePath)) {
            throw new IllegalArgumentException("Invalid source file: " + sourcePath);
        }
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
}