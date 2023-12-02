package ActionFolder;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

// WriteStringOnFileAction class
public class WriteStringOnFileAction implements Action {
    private String targetFilePath;
    private String stringToWrite;

    // Constructor
    public WriteStringOnFileAction(String targetFilePath, String stringToWrite) {
        if (!targetFilePath.toLowerCase().endsWith(".txt") && !targetFilePath.toLowerCase().endsWith(".text") ) {
            throw new IllegalArgumentException("Error: Unsupported file format. Please provide a .txt file.");
        
       
    }
    else {
        this.targetFilePath = targetFilePath;
        this.stringToWrite = stringToWrite;
        File file = new File(targetFilePath);
        if (!file.exists()) 
        throw new IllegalArgumentException("The file does not exist: ");
    }}

    @Override
    public void execute() {
        

        // Check if the file exists
      
        
        // Write to the file
        try (FileWriter fileWriter = new FileWriter(targetFilePath, true)) {
            fileWriter.write(stringToWrite + " ");
        } catch (IOException e) {
            // Handle the IO exception (you can print the stack trace or handle it differently)
            e.printStackTrace();
        }
    }
}

