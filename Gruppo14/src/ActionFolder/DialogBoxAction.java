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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((message == null) ? 0 : message.hashCode());
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
        DialogBoxAction other = (DialogBoxAction) obj;
        if (message == null) {
            if (other.message != null)
                return false;
        } else if (!message.equals(other.message))
            return false;
        return true;
    }
    
}

