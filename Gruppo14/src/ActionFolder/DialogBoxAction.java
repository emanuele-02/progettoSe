package ActionFolder;

import javax.swing.JOptionPane;


public class DialogBoxAction implements Action {
    private String message;

    // Costruttore della classe
    public DialogBoxAction(String message) {
        this.message = message;
    }

    // Implementazione del metodo execute
    @Override
    public void execute() {
        // viene mostrata la displaybox contenente il messaggio inserito dall' utente
        JOptionPane.showMessageDialog(null, message, "Dialog Box", JOptionPane.INFORMATION_MESSAGE);
    }

   

}
