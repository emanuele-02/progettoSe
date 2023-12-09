package ActionFolder;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.PosixFilePermission;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

public class DeleteFileActionTest {
    @Test
    void testvalidExecute() {

        DeleteFileAction deleteFileAction = new DeleteFileAction("D:/progettoSe/", "testDelete3.txt");

        assertTrue(deleteFileAction.getFile().exists());
        deleteFileAction.execute();
        assertFalse(deleteFileAction.getFile().exists());

    }

    @Test
    void testNoExistingFile() {

        assertThrows(RuntimeException.class, () -> new DeleteFileAction("D:/progettoSe/", "testDelete2.txt"));

    }

    @Test
    void testNoFilePermission() {
        // Creare l'oggetto DeleteFileAction
        DeleteFileAction deleteFileAction = new DeleteFileAction("D:/progettoSe/", "testDelete3.txt");

        // Ottenere il percorso del file
        Path filePath = deleteFileAction.getFile().toPath();

        try {
            // Create a set of permissions without a modify permission
            Set<PosixFilePermission> permissions = new HashSet<>();
            permissions.add(PosixFilePermission.OWNER_READ);
            permissions.add(PosixFilePermission.OWNER_WRITE);

            // Set new File permission
            Files.setPosixFilePermissions(filePath, permissions);

            assertThrows(RuntimeException.class, () -> deleteFileAction.execute());
        } catch (UnsupportedOperationException e) {
            // If the os doesn't support PosixFilePermission, jump the operation or try a
            // different one
            System.out.println("OS doesn't support POSIX");
        } catch (Exception e) {
            // Gestisci altre eccezioni qui
            e.printStackTrace();
        } finally {
            // Reset original file permissions
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
