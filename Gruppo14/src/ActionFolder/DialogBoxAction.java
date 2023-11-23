package ActionFolder;

import javax.swing.JOptionPane;


public class DialogBoxAction implements Action {
    private String message;


    public DialogBoxAction(String message) {
        this.message = message;
    }

    @Override
    public void execute() {
        // the displaybox containing the message entered by the user is shown
        JOptionPane.showMessageDialog(null, message, "Dialog Box", JOptionPane.INFORMATION_MESSAGE);
    }

   

}
