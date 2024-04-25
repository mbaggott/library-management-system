package controller;

import java.awt.event.*;
import javax.swing.*;
import view.RemoveVideoDialog;

//Controller for the RemoveVideo class.
public class RemoveVideoDialogController implements ActionListener {

    //Declare instance variables
    private RemoveVideoDialog removeVideo;
            
    // *** CONSTRUCTOR ***
    // Initialise instance variable
    public RemoveVideoDialogController(RemoveVideoDialog removeVideo) {
        this.removeVideo = removeVideo;
    }
    
    //Required override for ActionListener interface
    @Override
    public void actionPerformed(ActionEvent ae) {
        
        //Execute switch based on ActionCommand received from RemoveBook class 
        switch(ae.getActionCommand()) {
           
            //Action Command "OK"
            case "OK":
                //Initiate confirmation dialog
                int confirm = JOptionPane.showOptionDialog(removeVideo.getFrame(),
                        "Are you sure you wish to remove this video?",
                        "Exit Confirmation", JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE, null, null, null);
                //If confirmed
                if (confirm == 0) {     
                    //Set the book code in TopMenuController and 
                    //IconBarController for removal
                    removeVideo.getFrame().getTopMenu().getController()
                            .setRemoveVideoCode(removeVideo.getValue());
                    removeVideo.getFrame().getTopMenu().getIconBar()
                            .getController()
                            .setRemoveVideoCode(removeVideo.getValue());
                    //Remove the dialog and dispose of this object
                    removeVideo.setVisible(false);
                    removeVideo.dispose();
                }
                break;
            
            //Action Command "CANCEL"
            case "CANCEL":
                //Unset the remove book code in TopMenuController
                //and IconBarController
                removeVideo.getFrame().getTopMenu().getController()
                        .setRemoveVideoCode(null);
                removeVideo.getFrame().getTopMenu().getIconBar().getController()
                        .setRemoveVideoCode(null);
                //Remove the dialog and dispose of this object
                removeVideo.setVisible(false);
                removeVideo.dispose();
                break;
            default:
                break;
        }
    }
}
