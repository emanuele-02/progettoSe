package ActionFolder;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFilePermission;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class DeleteFileActionTest {

    private static final String TEST_DIRECTORY = "DeleteTestDirectory";
    private static final String TEST_FILE_NAME = "testDelete3.txt";

    @BeforeEach
    void setUp() {
        try {
            // Crate the directory if does not exist
            Path directoryPath = Paths.get(TEST_DIRECTORY);
            if (!Files.exists(directoryPath)) {
                Files.createDirectories(directoryPath);
            }

            // Create the file
            File testFile = new File(TEST_DIRECTORY, TEST_FILE_NAME);
            if (!testFile.exists() && !testFile.createNewFile()) {
                System.out.println("Error creating test file: " + testFile.getAbsolutePath());
                throw new RuntimeException("Error creating test file");
            }
        } catch (IOException e) {
            System.out.println("Error creating test file: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Test
    void testValidExecute() {
        DeleteFileAction deleteFileAction = new DeleteFileAction(TEST_DIRECTORY, TEST_FILE_NAME);

        assertTrue(deleteFileAction.getFile().exists());
        deleteFileAction.execute();
        assertFalse(deleteFileAction.getFile().exists());
    }

    @Test
    void testNoExistingFile() {
        assertThrows(RuntimeException.class, () -> new DeleteFileAction(TEST_DIRECTORY, "nonexistent.txt").execute());
    }

    @Test
    void testNoFilePermission() {
        DeleteFileAction deleteFileAction = new DeleteFileAction(TEST_DIRECTORY, TEST_FILE_NAME);

        Path filePath = deleteFileAction.getFile().toPath();

        try {
            Set<PosixFilePermission> permissions = new HashSet<>();
            permissions.add(PosixFilePermission.OWNER_READ);
            permissions.add(PosixFilePermission.OWNER_WRITE);

            Files.setPosixFilePermissions(filePath, permissions);

            assertThrows(RuntimeException.class, () -> deleteFileAction.execute());
        } catch (UnsupportedOperationException e) {
            System.out.println("OS doesn't support POSIX");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                Set<PosixFilePermission> originalPermissions = new HashSet<>();
                originalPermissions.add(PosixFilePermission.OWNER_READ);
                originalPermissions.add(PosixFilePermission.OWNER_WRITE);
                originalPermissions.add(PosixFilePermission.OWNER_EXECUTE);

                Files.setPosixFilePermissions(filePath, originalPermissions);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}