package ActionFolder;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class MoveCopyFileActionTest {
    @Test
    void testCopyExecute() {
        // Copy test: verify that execution does not throw exceptions
        MoveCopyFileAction copyAction = new MoveCopyFileAction(OperationFileType.COPY, "provaCopyMove.txt",
                "DirectoryProvaMove1", "DirectoryProvaMove2");
        assertDoesNotThrow(copyAction::execute);
    }

    @Test
    void testMoveExecute() {
        // Move test: verify that execution does not throw exceptions
        MoveCopyFileAction copyAction = new MoveCopyFileAction(OperationFileType.MOVE, "provaCopyMove.txt",
                "DirectoryProvaMove1", "DirectoryProvaMove2");
        assertDoesNotThrow(copyAction::execute);
    }

    @Test
    void testInvalidSourceFile() {
        // Test with invalid source file
        assertThrows(IllegalArgumentException.class, () -> new MoveCopyFileAction(OperationFileType.COPY,
                "fileNotFound.txt", "DirectoryProvaMove1", "DirectoryProvaMove2"));
    }

    @Test
    void testInvalidDirectorySourceFile() {
        // Test with invalid source directory

        assertThrows(IllegalArgumentException.class, () -> new MoveCopyFileAction(OperationFileType.COPY,
                "ProvaMove.txt", "DirectoryProvaMove3", "DirectoryProvaMove2"));
    }

}