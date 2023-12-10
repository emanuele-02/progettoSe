package ActionFolder;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MoveCopyFileActionTest {

    private final String testFileName = "provaCopyMove.txt";
    private final String sourceDirectory1 = "DirectoryProvaMove1";
    private final String sourceDirectory2 = "DirectoryProvaMove2";
    private final String targetDirectory = "DirectoryProva3";

    @BeforeEach
    void setUp() {

        createTestFile(sourceDirectory1, testFileName);
        createDirectory(sourceDirectory2);
        createDirectory(targetDirectory);
    }

    @AfterEach
    void tearDown() {

        deleteFile(sourceDirectory1, testFileName);
        deleteDirectory(sourceDirectory2);
        deleteDirectory(targetDirectory);
    }

    @Test
    void testCopyExecute() {
        // Copy test: verify that execution does not throw exceptions
        MoveCopyFileAction copyAction = new MoveCopyFileAction(OperationFileType.COPY, testFileName, sourceDirectory1, targetDirectory);
        assertDoesNotThrow(copyAction::execute);
    }

    @Test
    void testMoveExecute() {
        // Move test: verify that execution does not throw exceptions
        MoveCopyFileAction moveAction1 = new MoveCopyFileAction(OperationFileType.MOVE, testFileName, sourceDirectory1, sourceDirectory2);
        assertDoesNotThrow(moveAction1::execute);

        MoveCopyFileAction moveAction2 = new MoveCopyFileAction(OperationFileType.MOVE, testFileName, sourceDirectory2, sourceDirectory1);
        assertDoesNotThrow(moveAction2::execute);
    }

    @Test
    void testInvalidSourceFile() {
        // Test with invalid source file
        assertThrows(IllegalArgumentException.class, () -> new MoveCopyFileAction(OperationFileType.COPY, "fileNotFound.txt", sourceDirectory1, targetDirectory));
    }

    @Test
    void testInvalidDirectorySourceFile() {
        // Test with invalid source directory
        assertThrows(IllegalArgumentException.class, () -> new MoveCopyFileAction(OperationFileType.COPY, testFileName, "DirectoryProvaMove3", sourceDirectory2));
    }

    // Helper methods for creating and deleting files and directories
    private void createTestFile(String directory, String fileName) {
        Path filePath = Paths.get(directory, fileName);
        try {
            Files.createDirectories(filePath.getParent());
            Files.createFile(filePath);
        } catch (Exception e) {
            throw new RuntimeException("Error creating test file: " + e.getMessage(), e);
        }
    }

    private void createDirectory(String directory) {
        try {
            Files.createDirectories(Paths.get(directory));
        } catch (Exception e) {
            throw new RuntimeException("Error creating directory: " + e.getMessage(), e);
        }
    }

    private void deleteFile(String directory, String fileName) {
        try {
            Files.deleteIfExists(Paths.get(directory, fileName));
        } catch (Exception e) {
            throw new RuntimeException("Error deleting test file: " + e.getMessage(), e);
        }
    }

    private void deleteDirectory(String directory) {
        try {
            Files.walk(Paths.get(directory))
                    .sorted(java.util.Collections.reverseOrder())
                    .map(Path::toFile)
                    .forEach(java.io.File::delete);
        } catch (Exception e) {
            throw new RuntimeException("Error deleting directory: " + e.getMessage(), e);
        }
    }
}
