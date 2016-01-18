package lms.view;

import javax.swing.*;

@SuppressWarnings("serial")
//GUI CLass
public class VisitorHelpDialog extends JPanel {
    
    //Declare instance variables
    private String message;
    
    // *** CONSTRUCTOR ***
    public VisitorHelpDialog(ViewFrame frame) {
        //Set value for OK button
        Object[] options = {"OK"};
        setMessage(); //Exectute helper method, keeps constructor clean
        @SuppressWarnings("unused")
        //result variable not used, but constructore execution required
        //Create help dialog
        int result = JOptionPane.showOptionDialog(frame, message, "Visitor Pattern", 
                JOptionPane.PLAIN_MESSAGE, JOptionPane.QUESTION_MESSAGE, 
                null, options, options[0]);
    }
    
    //Assigns string to message variable, for use with constructor
    private void setMessage() {
        this.message = "This program utilises the Visitor Pattern to determine Holding type.\n"
                + "The pattern utilises two interfaces and a class to do this.\n"
                + "Inerface 1: Visitable - Allows a class to implement this interface\n"
                + "and provides the method to accept a holding of type Holding.\n"
                + "Interface 2: Visitor - the class HoldingVisitor implements this interface\n"
                + "providing the method allowing it to be a visited by a holding and have the\n "
                + "requested result returned.\n"
                + "Class 1: Holding Visitor - provides the response to the visit method.";
    }
    
}
