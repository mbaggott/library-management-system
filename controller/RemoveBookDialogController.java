package lms.controller;

import java.awt.event.*;
import javax.swing.*;
import lms.view.RemoveBookDialog;

//Controller for the RemoveBook class.
public class RemoveBookDialogController implements ActionListener {

    //Declare instance variables
    private RemoveBookDialog removeBook;
            
    // *** CONSTRUCTOR ***
    // Initialise instance variable
    public RemoveBookDialogController(RemoveBookDialog removeBook) {
        this.removeBook = removeBook;
    }
    
    //Required override for ActionListener interface
    @Override
    public void actionPerformed(ActionEvent ae) {
        
        //Execute switch based on ActionCommand received from RemoveBook class 
        switch(ae.getActionCommand()) {
            
            //Action Command "OK"
            case "OK":
                //Initiate confirmation dialog
                int confirm = JOptionPane.showOptionDialog(removeBook.getFrame(),
                        "Are you sure you wish to remove this book?",
                        "Exit Confirmation", JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE, null, null, null);
                //If confirmed
                if (confirm == 0) {       
                    //Set the book code in TopMenuController and 
                    //IconBarController for removal
                    removeBook.getFrame().getTopMenu().getController()
                            .setRemoveBookCode(removeBook.getValue());
                    removeBook.getFrame().getTopMenu().getIconBar()
                            .getController()
                            .setRemoveBookCode(removeBook.getValue());
                    //Remove the dialog and dispose of this object
                    removeBook.setVisible(false);
                    removeBook.dispose();
                }
                break;
            
            //Action Command CANCEL    
            case "CANCEL":
                //Unset the remove book code in TopMenuController
                //and IconBarController
                removeBook.getFrame().getTopMenu().getController()
                        .setRemoveBookCode(null);
                removeBook.getFrame().getTopMenu().getIconBar().getController()
                        .setRemoveBookCode(null);
                //Remove the dialog and dispose of this object
                removeBook.setVisible(false);
                removeBook.dispose();
                break;
            default:
                break;
        }
    }
}
