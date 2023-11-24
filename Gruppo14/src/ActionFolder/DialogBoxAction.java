package ActionFolder;
import javax.swing.JOptionPane;

// Implementation of the Action interface to display a dialog box with a message
public class DialogBoxAction implements Action {
    // Maximum length allowed for the message in the dialog box
    private static final int MAX_MESSAGE_LENGTH = 300;

    // The message to be displayed in the dialog box
    private String message;

    // Constructor with a check for message length
    public DialogBoxAction(String message) {
        // Check if the message length exceeds the maximum limit
        if (message.length() > MAX_MESSAGE_LENGTH) {
            throw new IllegalArgumentException("Message length exceeds the maximum limit of " + MAX_MESSAGE_LENGTH + " characters.");
        }
        this.message = message;
    }

    // Implementation of the execute method from the Action interface
    @Override
    public void execute() {
        // Display a dialog box containing the message
        // Parameters: null (for a default frame), message, title, message type
        JOptionPane.showMessageDialog(null, message, "Dialog Box", JOptionPane.INFORMATION_MESSAGE);
    }
}

