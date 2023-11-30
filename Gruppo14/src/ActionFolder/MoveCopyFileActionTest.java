package ActionFolder;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class MoveCopyFileActionTest {
    @Test
    void testCopyExecute() {
        // Copy test: verify that execution does not throw exceptions
        MoveCopyFileAction copyAction = new MoveCopyFileAction(OperationFileType.COPY, "provaCopyMove.txt", "C:\\Users\\giolu\\Desktop\\ProvaMove1", "C:\\Users\\giolu\\Desktop\\ProvaMove2");
        assertDoesNotThrow(copyAction::execute);
    }
 
    @Test
    void testMoveExecute() {
        // Move test: verify that execution does not throw exceptions
        MoveCopyFileAction copyAction = new MoveCopyFileAction(OperationFileType.MOVE,"provaCopyMove.txt", "C:\\Users\\giolu\\Desktop\\ProvaMove1", "C:\\Users\\giolu\\Desktop\\ProvaMove2");
        assertDoesNotThrow(copyAction::execute);
    }

    @Test
    void testInvalidSourceFile() {
        // Test with invalid source file
        MoveCopyFileAction copyAction = new MoveCopyFileAction(OperationFileType.COPY, "fileNotFound.txt", "C:\\Users\\giolu\\Desktop\\ProvaMove1", "C:\\Users\\giolu\\Desktop\\ProvaMove2");
        assertThrows(IllegalArgumentException.class, copyAction::execute);
    }

    @Test
    void testInvalidDirectorySourceFile() {
        // Test with invalid source directory
        MoveCopyFileAction copyAction = new MoveCopyFileAction(OperationFileType.COPY, "ProvaMove.txt", "C:\\Users\\giolu\\Desktop\\ProvaMove3", "C:\\Users\\giolu\\Desktop\\ProvaMove2");
        assertThrows(IllegalArgumentException.class, copyAction::execute);
    }

}