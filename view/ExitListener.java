package lms.view;

import java.awt.event.*;
import javax.swing.*;

//Listener for when programmed is exited via the cross in the top right hand corner
public class ExitListener implements WindowListener {

    //Declare instance variables
    private ViewFrame frame;
    
    //*** CONSTRUCTOR ***
    public ExitListener(ViewFrame frame) {
        this.frame = frame;
    }
    
    // *** Required overridden methods ***
    
    @Override
    //On closing of window request confirmation.
    //Do not close if confirmation not given.
    public void windowClosing(WindowEvent e) {
        int confirm = JOptionPane.showOptionDialog(frame,
                "Are you sure you wish to exit the Library Management System?",
                "Exit Confirmation", JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, null, null);
        if (confirm == 0) {
            System.exit(0);
        }
    }
    
    // *** UNUSED METHODS ***
    @Override
    public void windowActivated(WindowEvent e) {
    }

    @Override
    public void windowClosed(WindowEvent e) {
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
    }

    @Override
    public void windowIconified(WindowEvent e) { 
    }

    @Override
    public void windowOpened(WindowEvent e) {
    }

}
